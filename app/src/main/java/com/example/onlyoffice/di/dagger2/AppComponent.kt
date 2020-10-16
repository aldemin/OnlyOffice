package com.example.onlyoffice.di.dagger2

import com.example.onlyoffice.di.dagger2.modules.NavigationModule
import com.example.onlyoffice.mvp.presenters.MainActivityPresenter
import com.example.onlyoffice.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivityPresenter: MainActivityPresenter)
}