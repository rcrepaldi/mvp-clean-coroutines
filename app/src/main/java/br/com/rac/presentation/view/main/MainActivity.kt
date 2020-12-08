package br.com.rac.presentation.view.main

import android.os.Bundle
import br.com.rac.R
import br.com.rac.presentation.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<MainContract.Presenter, MainContract.View>(), MainContract.View {

    override fun createPresenter(): MainContract.Presenter {
        val mPresenter: MainPresenter by inject()
        return mPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter?.initPresenter()
        presenter?.changeMessage()


    /* ESSE SNIPPET ADICIONA AS GUIAS DE NAVEGAÇÂO */

        /*val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/
    }

    override fun initViews() {
        button_change_message.setOnClickListener {
            presenter?.changeMessage()
        }
    }

    override fun onTextMessageChange(message: String) {
        runOnUiThread {
            text_message?.text = message
        }
    }

    override fun onTextMessageWait() {
        runOnUiThread {
            text_message?.text = getString(R.string.wait)
        }
    }
}