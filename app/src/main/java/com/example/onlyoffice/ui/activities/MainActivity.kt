package com.example.onlyoffice.ui.activities

import android.os.Bundle
import com.example.onlyoffice.App
import com.example.onlyoffice.R
import com.example.onlyoffice.mvp.presenters.MainActivityPresenter
import com.example.onlyoffice.mvp.views.MainActivityView
import com.example.onlyoffice.common.navigation.cicerone.LoginFragmentAppScreen
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject
import javax.inject.Named

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @Inject
    @field:Named("AppRouter")
    lateinit var cicerone: Cicerone<Router>

    private var navigator: Navigator = SupportAppNavigator(this, R.id.ac_main_container)

    private val presenter by moxyPresenter { MainActivityPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        cicerone.navigatorHolder.removeNavigator()
    }

    override fun navToLoginScreen() {
        cicerone.router.replaceScreen(LoginFragmentAppScreen())
    }
}