package br.com.rac.presentation.internal.di

import br.com.rac.presentation.view.main.MainPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory { MainPresenter(get()) }
}