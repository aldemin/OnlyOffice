package com.example.onlyoffice.common.di.dagger2.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    fun context() = context

}