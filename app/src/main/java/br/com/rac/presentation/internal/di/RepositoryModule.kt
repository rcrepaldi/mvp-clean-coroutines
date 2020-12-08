package br.com.rac.presentation.internal.di

import br.com.rac.data.phrase.remote.PhraseRemoteDataSource
import br.com.rac.data.phrase.remote.PhraseRemoteDataSourceImpl
import br.com.rac.data.phrase.remote.PhraseRepositoryImpl
import br.com.rac.domain.phrase.repository.PhraseRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<PhraseRemoteDataSource> {
        PhraseRemoteDataSourceImpl(get(), get())
    }

    single<PhraseRepository> {
        PhraseRepositoryImpl(get())
    }

}