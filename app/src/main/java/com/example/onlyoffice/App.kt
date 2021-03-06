package com.example.onlyoffice

import android.app.Application
import com.example.onlyoffice.common.di.dagger2.AppComponent
import com.example.onlyoffice.common.di.dagger2.DaggerAppComponent
import com.example.onlyoffice.common.di.dagger2.modules.ContextModule

class App: Application() {

    companion object{
        lateinit var instance: App
    }

    init {
        instance = this
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this.applicationContext))
            .build()
    }

}