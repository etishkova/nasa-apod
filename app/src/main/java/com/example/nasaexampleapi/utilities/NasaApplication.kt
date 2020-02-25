package com.example.nasaexampleapi.utilities

import android.app.Application
import com.example.nasaexampleapi.di.ApplicationComponent
import com.example.nasaexampleapi.di.DaggerApplicationComponent
import com.example.nasaexampleapi.di.modules.application.ApplicationModule

class NasaApplication: Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent = applicationComponent
}