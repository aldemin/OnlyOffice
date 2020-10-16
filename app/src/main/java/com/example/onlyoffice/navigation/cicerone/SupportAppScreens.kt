package com.example.onlyoffice.navigation.cicerone

import com.example.onlyoffice.ui.fragments.LoginFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class LoginFragmentAppScreen : SupportAppScreen() {

    override fun getFragment() = LoginFragment.newInstance()
}