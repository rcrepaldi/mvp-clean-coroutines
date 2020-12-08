package br.com.rac.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity<T, in V> : AppCompatActivity(),
    BaseContract.View where T : BaseContract.Presenter<V>, V : BaseContract.View {

    protected var presenter: T? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }

    protected abstract fun createPresenter(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = this.createPresenter()
        try {
            presenter?.attachView(this as V)
        } catch (e: ClassCastException) {
            throw RuntimeException("Type V must be the same type of this class", e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter?.detachView()
        presenter = null
    }
}