package br.com.rac.domain.phrase.usecase

import br.com.rac.domain.phrase.model.Phrase
import kotlinx.coroutines.flow.Flow

interface PhraseUseCase {
    suspend fun requestPhrase(phraseNumber: String): Flow<Phrase>
}