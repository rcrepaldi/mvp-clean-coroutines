package br.com.rac.data.network.service

import br.com.rac.data.phrase.model.PhraseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PhraseService {

    @GET("chuck_norris/frases/{phraseNumber}/.json")
    fun fetchPhrase(@Path("phraseNumber") phraseNumber: String): Call<PhraseData>

}