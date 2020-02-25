package com.example.nasaexampleapi.di.modules.application

import android.app.Application
import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.domain.NasaApodRepository
import com.example.nasaexampleapi.network.ApodApi
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

}