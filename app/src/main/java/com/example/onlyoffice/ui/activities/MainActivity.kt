package com.example.onlyoffice.ui.activities

import android.os.Bundle
import android.view.MenuItem
import com.example.onlyoffice.R
import com.example.onlyoffice.mvp.presenters.MainActivityPresenter
import com.example.onlyoffice.mvp.views.MainActivityView
import com.example.onlyoffice.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    private var navigator: Navigator = SupportAppNavigator(this, R.id.ac_main_container)
    private val presenter by moxyPresenter { MainActivityPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        presenter.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        presenter.cicerone.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.ac_main_container)
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()) {
            return
        } else {
            super.onBackPressed()
        }
    }

}