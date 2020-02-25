package com.example.nasaexampleapi.di.modules.presentation

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activity: FragmentActivity) {

    @Provides
    fun getActivity(): Activity {
        return activity
    }
}