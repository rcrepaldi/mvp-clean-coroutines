package br.com.rac.presentation.view.main

import br.com.rac.presentation.view.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun initViews()
        fun onTextMessageChange(message: String)
        fun onTextMessageWait()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun initPresenter()
        fun changeMessage()
    }
}
