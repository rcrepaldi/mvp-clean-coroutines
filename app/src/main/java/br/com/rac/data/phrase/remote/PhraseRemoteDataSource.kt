package br.com.rac.data.phrase.remote

import br.com.rac.data.phrase.model.PhraseData

interface PhraseRemoteDataSource {
    suspend fun requestPhrase(phraseNumber: String): PhraseData
}