package com.example.onlyoffice.mvp.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainActivityView: MvpView {
    @AddToEndSingle
    fun navToLoginScreen()
}