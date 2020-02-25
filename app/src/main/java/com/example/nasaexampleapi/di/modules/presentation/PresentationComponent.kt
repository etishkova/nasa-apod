package com.example.nasaexampleapi.di.modules.presentation

import com.example.nasaexampleapi.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)
}