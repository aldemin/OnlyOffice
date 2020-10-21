package com.example.onlyoffice.ui.activities

import android.os.Bundle
import android.view.MenuItem
import com.example.onlyoffice.R
import com.example.onlyoffice.mvp.presenters.MainActivityPresenter
import com.example.onlyoffice.mvp.views.MainActivityView
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> presenter.cicerone.router.exit()
        }
        return super.onOptionsItemSelected(item)
    }

}