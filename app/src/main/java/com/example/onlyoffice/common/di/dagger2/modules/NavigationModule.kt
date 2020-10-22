package com.example.onlyoffice.common.di.dagger2.modules

import com.example.onlyoffice.common.navigation.cicerone.NavigationHolder
import com.example.onlyoffice.common.navigation.cicerone.NavigationHolder.LocalCiceroneTag.*
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NavigationModule {

    private val navigationHolder = NavigationHolder()

    @Provides
    @Named("AppRouter")
    fun appRouter() = navigationHolder.getCicerone(APP)

    @Provides
    @Named("MainRouter")
    fun mainRouter() = navigationHolder.getCicerone(MAIN)

}