package br.com.rac.data.network

import br.com.rac.domain.exceptions.HttpServerErrorException
import br.com.rac.domain.exceptions.MyIOException
import retrofit2.Call
import retrofit2.Response

private const val DEFAULT_MAX_ATTEMPTS = 2


/**
 * Use this when you want that a retrofit call automatically retries if some 5xx or networking error
 * occurs
 * @throws IllegalArgumentException if [maxAttempts] < 1
 */
internal suspend fun <T> withRetry(
    call: Call<T>,
    delay: CallRetryDelays,
    maxAttempts: Int = DEFAULT_MAX_ATTEMPTS,
    makeCall: suspend (Call<T>) -> Response<T>
): Response<T> {

    require(maxAttempts >= 1) { "max retry attempts should be at least 1" }

    var currentAttempt = 0

    suspend fun <T> tryToMakeCall(
        call: Call<T>,
        makeCall: suspend (Call<T>) -> Response<T>
    ): Response<T> {

        currentAttempt++

        try {
            return makeCall(call.clone())
        } catch (e: Exception) {

            if (e is HttpServerErrorException || e is MyIOException) {
                if (currentAttempt == 1) {
                    kotlinx.coroutines.delay(delay.firstDelayInMills)
                    return tryToMakeCall(call, makeCall)
                } else if (currentAttempt <= maxAttempts) {
                    kotlinx.coroutines.delay(delay.secondDelayInMills)
                    return tryToMakeCall(call, makeCall)
                }
            }

            throw e
        }
    }

    return tryToMakeCall(call, makeCall)

}