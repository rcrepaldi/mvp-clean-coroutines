package br.com.rac.data.phrase.remote

import br.com.rac.data.phrase.model.toPhraseMapper
import br.com.rac.domain.phrase.model.Phrase
import br.com.rac.domain.phrase.repository.PhraseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhraseRepositoryImpl constructor(private val phraseRemoteDataSource: PhraseRemoteDataSource) :
    PhraseRepository {

    override suspend fun requestPhrase(phraseNumber: String): Flow<Phrase> = flow {
        try {
            val phraseResponse = phraseRemoteDataSource.requestPhrase(phraseNumber).toPhraseMapper()
            emit(phraseResponse)
        } catch (e: Exception) {
            throw e
        }
    }


}