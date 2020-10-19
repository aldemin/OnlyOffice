package com.example.onlyoffice.common.providers

import android.content.Context
import com.example.onlyoffice.R

class LoginFragmentStringProvider(private val context: Context) {

    val portalErrorMessage = context.getString(R.string.portalErrorMessage)
    val loginErrorMessage = context.getString(R.string.loginErrorMessage)
    val passwordErrorMessage = context.getString(R.string.passwordErrorMessage)
    val headerErrorDialog = context.getString(R.string.headerErrorDialog)

}