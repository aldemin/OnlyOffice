package com.example.onlyoffice.mvp.presenters

import com.example.onlyoffice.App
import com.example.onlyoffice.common.navigation.cicerone.LoginFragmentAppScreen
import com.example.onlyoffice.common.navigation.cicerone.MainFragmentAppScreen
import com.example.onlyoffice.common.providers.AuthInfoProvider
import com.example.onlyoffice.common.providers.DEF_VALUE
import com.example.onlyoffice.mvp.views.MainActivityView
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {

    @Inject
    @field:Named("AppRouter")
    lateinit var cicerone: Cicerone<Router>
    @Inject
    lateinit var authInfoProvider: AuthInfoProvider

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        if (authInfoProvider.token.equals(DEF_VALUE)) {
            moveToLoginScreen()
        } else {
            moveToMainScreen()
        }
    }

    fun moveToLoginScreen(){
        cicerone.router.replaceScreen(LoginFragmentAppScreen())
    }

    fun moveToMainScreen(){
        cicerone.router.replaceScreen(MainFragmentAppScreen())
    }

}