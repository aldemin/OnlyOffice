package com.example.onlyoffice.common.navigation.cicerone

import com.example.onlyoffice.ui.fragments.DocumentsFragment
import com.example.onlyoffice.ui.fragments.LoginFragment
import com.example.onlyoffice.ui.fragments.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class LoginFragmentAppScreen : SupportAppScreen() {
    override fun getFragment() = LoginFragment.newInstance()
}

class MainFragmentAppScreen : SupportAppScreen() {
    override fun getFragment() = MainFragment.newInstance()
}

class DocumentsFragmentAppScreen(
    private val folderId: String
) : SupportAppScreen() {
    override fun getFragment() = DocumentsFragment.newInstance(folderId)
}