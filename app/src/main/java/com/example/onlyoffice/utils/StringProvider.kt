package com.example.onlyoffice.utils

import android.content.Context
import com.example.onlyoffice.R

class LoginFragmentStringProvider(private val context: Context) {

    val portalErrorMessage = context.getString(R.string.portalErrorMessage)
    val loginErrorMessage = context.getString(R.string.loginErrorMessage)
    val passwordErrorMessage = context.getString(R.string.passwordErrorMessage)

}