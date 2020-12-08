package br.com.rac.presentation.internal.di

import br.com.rac.BuildConfig
import br.com.rac.data.network.ApiClientBuilder
import br.com.rac.data.network.CallRetryDelays
import br.com.rac.data.network.NetworkConnection
import br.com.rac.data.network.interceptor.MyAccessTokenInterceptor
import br.com.rac.data.network.request.RequestMaker
import br.com.rac.data.network.request.RequestMakerImpl
import br.com.rac.data.network.service.PhraseService
import org.koin.dsl.module

val networkServiceModule = module {

    single<RequestMaker> {
        RequestMakerImpl(get(), get())
    }

    single {
        NetworkConnection(get())
    }

    single {
        CallRetryDelays()
    }

    single {
        ApiClientBuilder.createService(
            PhraseService::class.java, BuildConfig.BASE_URL,
            interceptors = arrayOf(MyAccessTokenInterceptor())
        )
    }
}