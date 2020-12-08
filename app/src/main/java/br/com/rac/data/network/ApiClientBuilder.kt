package br.com.rac.data.network

import br.com.rac.data.network.interceptor.getSimpleLogging
import br.com.rac.presentation.util.extension.runWhenDebug
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClientBuilder {

    companion object {

        fun <S> createService(
            serviceClass: Class<S>,
            baseUrl: String,
            readTimeoutInMills: Long = 150000L,
            gson: Gson = Gson(),
            vararg interceptors: Interceptor
        ): S {

            val httpClientBuilder = OkHttpClient.Builder()
            interceptors.forEach { interceptor -> httpClientBuilder.addInterceptor(interceptor) }

            runWhenDebug {
                // Critical part, LogClient must be last one if you have more interceptors
                httpClientBuilder.addInterceptor(HttpLoggingInterceptor().getSimpleLogging())
            }

            val client = httpClientBuilder
                .readTimeout(readTimeoutInMills, TimeUnit.MILLISECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()


            val retrofit = getClientBuilder(baseUrl, gson)
                .client(client)
                .build()
            return retrofit.create(serviceClass)
        }

        private fun getClientBuilder(baseUrl: String, gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
        }
    }

}
