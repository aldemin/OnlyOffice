package com.example.onlyoffice.di.dagger2.modules

import com.example.onlyoffice.navigation.cicerone.NavigationHolder
import com.example.onlyoffice.navigation.cicerone.NavigationHolder.LocalCiceroneTag.APP
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