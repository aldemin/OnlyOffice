package com.example.onlyoffice.mvp.presenters

import com.example.onlyoffice.App
import com.example.onlyoffice.common.navigation.cicerone.LoginFragmentAppScreen
import com.example.onlyoffice.common.navigation.cicerone.MainFragmentAppScreen
import com.example.onlyoffice.common.providers.AuthInfoProvider
import com.example.onlyoffice.mvp.views.MainActivityView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class MainActivityPresenter : MvpPresenter<MainActivityView>() {

    @Inject
    @field:Named("AppRouter")
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var authInfoProvider: AuthInfoProvider

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        if (authInfoProvider.isAuthorization()) {
            moveToMainScreen()
        } else {
            moveToLoginScreen()
        }
    }

    private fun moveToLoginScreen() {
        cicerone.router.replaceScreen(LoginFragmentAppScreen())
    }

    private fun moveToMainScreen() {
        cicerone.router.replaceScreen(MainFragmentAppScreen())
    }

}