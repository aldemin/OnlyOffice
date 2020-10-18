package com.example.onlyoffice.common.di.dagger2.modules

import android.content.Context
import com.example.onlyoffice.utils.LoginFragmentStringProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StringProviderModule {

    @Singleton
    @Provides
    fun loginFragmentStringProvider(context: Context) = LoginFragmentStringProvider(context)

}