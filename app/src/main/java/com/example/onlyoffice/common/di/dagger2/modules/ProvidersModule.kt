package com.example.onlyoffice.common.di.dagger2.modules

import android.content.Context
import com.example.onlyoffice.common.providers.AuthInfoProvider
import com.example.onlyoffice.common.providers.LoginFragmentStringProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProvidersModule {

    @Singleton
    @Provides
    fun loginFragmentStringProvider(context: Context) = LoginFragmentStringProvider(context)

    @Singleton
    @Provides
    fun authInfoProvider(context: Context) = AuthInfoProvider(context)

}