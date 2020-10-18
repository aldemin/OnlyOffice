package com.example.onlyoffice.mvp.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface LoginFragmentView: MvpView {

    @AddToEndSingle
    fun toggleLoadingDialog(isShowing: Boolean)
    @AddToEndSingle
    fun setPortalError(message: String)
    @AddToEndSingle
    fun setLoginError(message: String)
    @AddToEndSingle
    fun setPasswordError(message: String)
    @AddToEndSingle
    fun showErrorDialog(message: String)
    @AddToEndSingle
    fun moveToMainScreen()
}
