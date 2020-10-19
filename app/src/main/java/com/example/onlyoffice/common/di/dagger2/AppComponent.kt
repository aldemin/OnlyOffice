package com.example.onlyoffice.common.di.dagger2

import com.example.onlyoffice.common.di.dagger2.modules.ContextModule
import com.example.onlyoffice.common.di.dagger2.modules.NavigationModule
import com.example.onlyoffice.common.di.dagger2.modules.RetrofitModule
import com.example.onlyoffice.common.di.dagger2.modules.ProvidersModule
import com.example.onlyoffice.mvp.presenters.LoginFragmentPresenter
import com.example.onlyoffice.mvp.presenters.MainActivityPresenter
import com.example.onlyoffice.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NavigationModule::class,
        ContextModule::class,
        RetrofitModule::class,
        ProvidersModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivityPresenter: MainActivityPresenter)
    fun inject(loginFragmentPresenter: LoginFragmentPresenter)
}