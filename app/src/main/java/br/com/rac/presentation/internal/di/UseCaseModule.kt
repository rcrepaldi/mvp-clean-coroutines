package br.com.rac.presentation.internal.di

import br.com.rac.domain.phrase.usecase.PhraseUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {

    factory { PhraseUseCaseImpl(get()) }
}