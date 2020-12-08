package br.com.rac.presentation.internal.di

import br.com.rac.domain.phrase.usecase.PhraseUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { PhraseUseCase(get()) }
}