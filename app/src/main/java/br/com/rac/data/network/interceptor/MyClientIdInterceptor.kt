package br.com.rac.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class MyClientIdInterceptor constructor(private val clientId: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("clientId", clientId)
            .addHeader("Accept-Language", "pt-BR")
            .build()

        return chain.proceed(newRequest)
    }
}