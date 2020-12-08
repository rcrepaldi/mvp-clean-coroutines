package br.com.rac.data.network.request

import android.util.Log
import br.com.rac.data.network.CallRetryDelays
import br.com.rac.data.network.NetworkConnection
import br.com.rac.data.network.base.ResponseBase
import br.com.rac.data.network.base.ResponseEmptyBase
import br.com.rac.data.network.withRetry
import br.com.rac.domain.exceptions.*
import br.com.rac.domain.exceptions.entity.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import kotlin.coroutines.resumeWithException


class RequestMakerImpl constructor(
    private val networkConnection: NetworkConnection,
    private val callRetryDelays: CallRetryDelays
) :
    RequestMaker {

    override suspend fun <T : Any> getResult(call: Call<T>): ResponseBase<T> =
        get(call).let {
            val body = it.body() ?: throw Exception("Unexpected exception on request maker")
            return ResponseBase(body, it.headers().toMultimap())
        }

    override suspend fun <T : Any> getEmptyResult(
        call: Call<T>,
        tokenExpired: Boolean
    ): ResponseEmptyBase =
        get(call).let {
            return ResponseEmptyBase(Unit)
        }

    private suspend fun <T : Any> get(call: Call<T>): Response<T> =
        withRetry(call, callRetryDelays) {
            try {
                val result = it.await()
                result
            } catch (e: HttpException) {
                throw getException(e)
            } catch (e: IOException) {
                val message = "IOError in call, url: ${call.request().url}"
                if ((e is UnknownHostException || e is ConnectException)
                    && !networkConnection.isNetworkConnected()
                ) {
                    throw MyNoNetworkConnectionException(message, e)
                }
                throw MyIOException(message, e)
            } catch (e: Exception) {
                Log.e(e.message, "unexpected exception on request maker ${e.javaClass.simpleName}")
                throw e
            }
        }

    private fun getException(e: HttpException): Exception = when (e.code()) {
        404 -> {
            UserException(
                e.message(),
                e,
                Gson().fromJson<ErrorResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
            )
        }
        400, in 405..499 -> HttpClientErrorException(
            e.message(),
            e.code(),
            e,
            Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
        )
        502, in 405..499 -> HttpClientErrorException(
            e.message(),
            e.code(),
            e,
            Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
        )
        else -> HttpServerErrorException(e.message(), e.code(), e)
    }

    private suspend fun <T : Any> Call<T>.await(): Response<T> {
        return suspendCancellableCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>?, response: Response<T>) {
                    continuation.resumeWith(runCatching {
                        if (response.isSuccessful) {
                            response
                        } else {
                            throw HttpException(response)
                        }
                    })
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    // Don't bother with resuming the continuation if it is already cancelled.
                    if (continuation.isCancelled) return
                    continuation.resumeWithException(t)
                }
            })

            registerOnCompletion(continuation)
        }
    }

    private fun Call<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
        continuation.invokeOnCancellation {
            try {
                cancel()
            } catch (ex: Throwable) {
                //Ignore cancel exception
            }
        }
    }
}
