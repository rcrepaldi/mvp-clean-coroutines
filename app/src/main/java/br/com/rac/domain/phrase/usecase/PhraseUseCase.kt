package br.com.rac.domain.phrase.usecase

import br.com.rac.domain.phrase.model.Phrase
import br.com.rac.domain.phrase.repository.PhraseRepository
import kotlinx.coroutines.flow.Flow

class PhraseUseCase constructor(private val phraseRepository: PhraseRepository) {

    suspend fun requestPhrase(phraseNumber: String): Flow<Phrase> {
        return phraseRepository.requestPhrase(phraseNumber)
    }
}
