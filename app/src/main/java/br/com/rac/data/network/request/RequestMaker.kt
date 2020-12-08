package br.com.rac.data.network.request

import br.com.rac.data.network.base.ResponseBase
import br.com.rac.data.network.base.ResponseEmptyBase
import br.com.rac.domain.exceptions.MyException
import retrofit2.Call

interface RequestMaker {
    @Throws(MyException::class)
    suspend fun <T : Any> getResult(call: Call<T>): ResponseBase<T>

    @Throws(MyException::class)
    suspend fun <T : Any> getEmptyResult(
        call: Call<T>,
        tokenExpired: Boolean = false
    ): ResponseEmptyBase
}