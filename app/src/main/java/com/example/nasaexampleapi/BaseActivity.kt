package com.example.nasaexampleapi

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.example.nasaexampleapi.di.ApplicationComponent
import com.example.nasaexampleapi.di.modules.presentation.PresentationComponent
import com.example.nasaexampleapi.di.modules.presentation.PresentationModule
import com.example.nasaexampleapi.utilities.NasaApplication

open class BaseActivity: AppCompatActivity() {

    private var isInjectorUsed = false

    @UiThread
    fun getPresentationComponent(): PresentationComponent {
        if (isInjectorUsed){
            throw RuntimeException("There is no need to use injector more than once")
        }
        isInjectorUsed = true
        return getApplicationComponent()
            .newPresentationComponent(PresentationModule(this))
    }


    private fun getApplicationComponent(): ApplicationComponent {
        return (application as NasaApplication).getApplicationComponent()
    }
}