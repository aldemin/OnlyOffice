package com.example.onlyoffice.common.di.dagger2.modules

import com.example.onlyoffice.common.navigation.cicerone.NavigationHolder
import com.example.onlyoffice.common.navigation.cicerone.NavigationHolder.LocalCiceroneTag.APP
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NavigationModule {

    private val navigationHolder = NavigationHolder()

    @Provides
    @Named("AppRouter")
    fun appRouter() = navigationHolder.getCicerone(APP)

}