package br.com.rac.presentation.view.main

import br.com.rac.domain.phrase.usecase.PhraseUseCase
import br.com.rac.presentation.view.BasePresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainPresenter constructor(private val phraseUseCase: PhraseUseCase) :
    BasePresenter<MainContract.View>(), MainContract.Presenter {

    companion object {
        private const val ERROR_MESSAGE = "Ooopss!"
    }

    override fun initPresenter() {
        view?.initViews()
    }

    override fun changeMessage() {
        requestPhrase()
    }

    fun requestPhrase() = GlobalScope.launch {
        view?.onTextMessageWait()
        try {
            val response = phraseUseCase.requestPhrase(Random.nextInt(1, 114).toString())
            response.collect {
                view?.onTextMessageChange(it.phrase)
            }
        } catch (e: Exception) {
            view?.onTextMessageChange(ERROR_MESSAGE)
        }
    }
}
