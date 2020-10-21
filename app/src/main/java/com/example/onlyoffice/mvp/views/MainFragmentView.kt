package com.example.onlyoffice.mvp.views

import com.example.onlyoffice.common.api.only_office.responses.UserInfoResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.terrakok.cicerone.android.support.SupportAppNavigator

interface MainFragmentView: MvpView {
    @AddToEndSingle
    fun toggleHeaderLoading(isShowing: Boolean)
    @AddToEndSingle
    fun updateUserInfoToHeader(userInfo: UserInfoResponse)
    @AddToEndSingle
    fun toggleHeaderPlaceholder(isShowing: Boolean)
}