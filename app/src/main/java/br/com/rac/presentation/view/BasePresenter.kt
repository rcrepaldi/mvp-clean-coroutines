package br.com.rac.presentation.view

import androidx.annotation.CallSuper
import kotlinx.coroutines.Job

abstract class BasePresenter<T : BaseContract.View> : BaseContract.Presenter<T> {

    protected val job = Job().also { job ->
        job.cancel()
    }


    fun pendingDisposables(): Job = Job().also { job ->
        job.cancel()
    }


    protected var view: T? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }

    @CallSuper
    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    @CallSuper
    override fun onDestroy() = job.cancel()

}
