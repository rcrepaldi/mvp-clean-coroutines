package br.com.rac.data.phrase.remote

import br.com.rac.data.phrase.model.PhraseData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PhraseRepositoryImplTest {

    private val mockPhraseRemoteDataSource = mockk<PhraseRemoteDataSource>()

    private val phraseRepositoryImpl = PhraseRepositoryImpl(mockPhraseRemoteDataSource)

    @Test
    fun `requestPhrase should return expected result`() {

        // Arrange
        val phraseNumber = "1"
        val phrase = "Hello world!"
        val expectedPhrase = PhraseData("37846932", "2", "7", "fulano", phrase)
        coEvery { mockPhraseRemoteDataSource.requestPhrase(phraseNumber) } returns expectedPhrase

        runBlocking {

            // Act
            val actualPhrase = phraseRepositoryImpl.requestPhrase(phraseNumber).single().phrase

            // Assert
            assertEquals(expectedPhrase.phrase, actualPhrase)
        }
    }
}