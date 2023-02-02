package br.com.rac.domain.phrase.usecase

import br.com.rac.domain.phrase.model.Phrase
import br.com.rac.domain.phrase.repository.PhraseRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhraseUseCaseImplTest : AutoCloseKoinTest() {

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

            // Arrange
            val phrase = "Hello world!"
            val expectedPhrase = Phrase("37846932", "2", "7", "fulano", phrase)
            val phraseNumber = "123"
            `when`(phraseRepository.requestPhrase(phraseNumber)).thenReturn(flow {
                emit(
                    expectedPhrase
                )
            })


            // Action
            val result = phraseRepository.requestPhrase(phraseNumber).single().phrase


            // Assert
            assertEquals(expectedPhrase.phrase, result)
            verify(phraseRepository).requestPhrase(phraseNumber)

        }
    }
}