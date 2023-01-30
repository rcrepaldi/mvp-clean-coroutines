package br.com.rac.presentation.view.main

import br.com.rac.domain.phrase.model.Phrase
import br.com.rac.domain.phrase.usecase.PhraseUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    private lateinit var phraseUseCase: PhraseUseCase

    @Mock
    private lateinit var mainContract: MainContract

    @Before
    fun setUp() {
        // Inicializa as anotações de mock
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun whenRequestPhraseReturnPhraseTest() {

        runBlocking {

            // Arrange
            val randomPosition = "55"
            val phrase = "Lorem ipsum dolor sit amet"
            val expectedPhrase = Phrase("37846932", "2", "7", "fulano", phrase)
            `when`(phraseUseCase.requestPhrase(randomPosition)).thenReturn(flow {
                emit(
                    expectedPhrase
                )
            })

            // Action
            val response = phraseUseCase.requestPhrase(randomPosition)
            val actualPhrase = response.single().phrase

            // Assert
            assertEquals(expectedPhrase.phrase, actualPhrase)
            verify(phraseUseCase).requestPhrase(randomPosition)
        }
    }

    @After
    fun tearDown() {
        // Limpeza depois dos testes
    }
}