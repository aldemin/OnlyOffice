package com.example.onlyoffice.common.providers

import android.content.Context
import android.content.Context.MODE_PRIVATE

private const val PREF_NAME = "authentication user info"

private const val TOKEN = "authentication token"
private const val EXPIRES = "authentication token expires"
private const val PORTAL = "user portal url"

const val DEF_VALUE = ""

class AuthInfoProvider(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    var token = sharedPreferences.getString(TOKEN, DEF_VALUE)
    set(value) {
        field = value
        sharedPreferences.edit()
            .putString(TOKEN, value)
            .apply()
    }

    var expires = sharedPreferences.getString(EXPIRES, DEF_VALUE)
    set(value) {
        field = value
        sharedPreferences.edit()
            .putString(EXPIRES, value)
            .apply()
    }

    var portal = sharedPreferences.getString(PORTAL, DEF_VALUE)
        set(value) {
            field = value
            sharedPreferences.edit()
                .putString(PORTAL, value)
                .apply()
        }

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }

    fun isAuthorization() = !token.isNullOrEmpty() || !expires.isNullOrEmpty() || !portal.isNullOrEmpty()
}