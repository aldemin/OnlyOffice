package com.example.onlyoffice.mvp.views

import com.example.onlyoffice.common.list_adapters.ViewType
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface DocumentsFragmentView: MvpView {
    @AddToEndSingle
    fun updateItemList(items: ArrayList<ViewType>)
    @AddToEndSingle
    fun toggleLoading(isShowing: Boolean)
    @AddToEndSingle
    fun togglePlaceholder(isShowing: Boolean)
    @AddToEndSingle
    fun toggleItemList(isShowing: Boolean)
    @AddToEndSingle
    fun showErrorDialog(header: String, message: String)
}