package br.com.rac.data.phrase.remote

import br.com.rac.data.network.request.RequestMaker
import br.com.rac.data.network.service.PhraseService
import br.com.rac.data.phrase.model.PhraseData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PhraseRemoteDataSourceImplTest {

    private val mockPhraseService = mockk<PhraseService>()
    private val mockRequestMaker = mockk<RequestMaker>()

    private val phraseRemoteDataSourceImpl = PhraseRemoteDataSourceImpl(
        phraseService = mockPhraseService,
        requestMaker = mockRequestMaker
    )

    @Test
    fun `requestPhrase should return expected result`() {

        runBlocking {

            // Given
            val phraseNumber = "1"
            val expectedPhraseData = PhraseData("Test Phrase")
            coEvery { mockPhraseService.fetchPhrase(phraseNumber = phraseNumber) } returns mockk()
            coEvery { mockRequestMaker.getResult<PhraseData>(any()).body } returns expectedPhraseData

            // When
            val actualPhraseData = phraseRemoteDataSourceImpl.requestPhrase(phraseNumber)

            // Then
            assertEquals(expectedPhraseData, actualPhraseData)
            verify { mockPhraseService.fetchPhrase(phraseNumber = phraseNumber) }
            coVerify { mockRequestMaker.getResult<PhraseData>(any()) }
        }
    }
}