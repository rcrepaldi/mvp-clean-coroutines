package br.com.rac.data.phrase.remote

import br.com.rac.data.network.request.RequestMaker
import br.com.rac.data.network.service.PhraseService
import br.com.rac.data.phrase.model.PhraseData

class PhraseRemoteDataSourceImpl constructor(
    private val phraseService: PhraseService,
    private val requestMaker: RequestMaker
) : PhraseRemoteDataSource {

    override suspend fun requestPhrase(phraseNumber: String): PhraseData {
        val call = phraseService.fetchPhrase(phraseNumber = phraseNumber)
        return requestMaker.getResult(call).body
    }
}