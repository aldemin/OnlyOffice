package com.example.onlyoffice.mvp.presenters

import com.example.onlyoffice.mvp.views.MainActivityView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.navToLoginScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}