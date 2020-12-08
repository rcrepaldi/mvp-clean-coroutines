package br.com.rac.domain.phrase.repository

import br.com.rac.domain.phrase.model.Phrase
import kotlinx.coroutines.flow.Flow

interface PhraseRepository {
    suspend fun requestPhrase(phraseNumber: String): Flow<Phrase>
}
