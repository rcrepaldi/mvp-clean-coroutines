package br.com.rac.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class MyAccessTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // val token = securedSharedPreferences.getValue(PreferencesKey.TOKEN, "")

        val newRequest = originalRequest.newBuilder()
            //.addHeader(AUTHORIZATION, "Bearer $token")
            .addHeader("Accept-Language", "pt-BR")
            .build()

        return chain.proceed(newRequest)

    }
}