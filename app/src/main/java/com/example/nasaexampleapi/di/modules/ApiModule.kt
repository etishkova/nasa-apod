package com.example.nasaexampleapi.di.modules

import com.example.nasaexampleapi.di.interfaces.ApplicationScope
import com.example.nasaexampleapi.network.ApodApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    @ApplicationScope
    fun provideApodApi(@ApplicationScope retrofit: Retrofit): ApodApi {
        return retrofit.create(ApodApi::class.java)
    }
}