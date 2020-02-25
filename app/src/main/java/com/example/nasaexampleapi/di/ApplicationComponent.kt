package com.example.nasaexampleapi.di

import com.example.nasaexampleapi.di.modules.application.ApplicationModule
import com.example.nasaexampleapi.di.modules.application.NetworkModule
import com.example.nasaexampleapi.di.modules.presentation.PresentationComponent
import com.example.nasaexampleapi.di.modules.presentation.PresentationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}