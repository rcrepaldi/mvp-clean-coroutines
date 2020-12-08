package br.com.rac.presentation.internal.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initInjector()
    }

    private fun initInjector() {
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(listOf(presenterModule, useCaseModule, repositoryModule, networkServiceModule))
        }
    }
}
