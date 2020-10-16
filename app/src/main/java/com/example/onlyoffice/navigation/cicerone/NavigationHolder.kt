package com.example.onlyoffice.navigation.cicerone

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class NavigationHolder {

    private val cicerones = hashMapOf<LocalCiceroneTag, Cicerone<Router>>()

    fun getCicerone(localCiceroneTag: LocalCiceroneTag) = cicerones.getOrPut(localCiceroneTag) {
        Cicerone.create()
    }

    enum class LocalCiceroneTag(val value: String) {
        APP("app")
    }

}