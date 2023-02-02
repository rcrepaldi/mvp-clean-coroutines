package br.com.rac.domain.phrase.usecase

import br.com.rac.domain.phrase.model.Phrase
import br.com.rac.domain.phrase.repository.PhraseRepository
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.MockitoAnnotations

@RunWith(MockitoJUnitRunner::class)
class PhraseUseCaseImplTest: AutoCloseKoinTest() {

    @Mock
    private lateinit var phraseRepository: PhraseRepository

    @Before
    fun setTup() {
        // Inicializa as anotações de mock
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test requestPhrase`() {
        runBlocking {

            val phrase = "Hello world!"
            val expectedPhrase = Phrase("37846932", "2", "7", "fulano", phrase)
            val phraseNumber = "123"
            `when`(phraseRepository.requestPhrase(phraseNumber)).thenReturn(flow { emit(expectedPhrase) })

            val result = phraseRepository.requestPhrase(phraseNumber).first()

            assertEquals(expectedPhrase, result)
            verify(phraseRepository).requestPhrase(phraseNumber)

        }
    }
}