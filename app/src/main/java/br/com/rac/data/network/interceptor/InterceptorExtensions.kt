package br.com.rac.data.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor

/**
 * Extension function that returns a simple logging with the level of
 *  [HttpLoggingInterceptor.Level.BODY]
 */
fun HttpLoggingInterceptor.getSimpleLogging(): HttpLoggingInterceptor {

    val logging = this
    // logging: use NONE | HEADERS | BASIC | BODY to see request data
    logging.level = HttpLoggingInterceptor.Level.BODY

    return logging

}