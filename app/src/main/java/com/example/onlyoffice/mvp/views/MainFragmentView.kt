package com.example.onlyoffice.mvp.views

import com.example.onlyoffice.model.responses.UserInfoResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainFragmentView: MvpView {
    @AddToEndSingle
    fun toggleHeaderLoading(isShowing: Boolean)
    @AddToEndSingle
    fun updateUserInfoToHeader(userInfo: UserInfoResponse)
    @AddToEndSingle
    fun setToolbarTitle(title: String)
}