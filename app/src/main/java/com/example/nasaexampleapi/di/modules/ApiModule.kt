package com.example.nasaexampleapi.di.modules

import com.example.nasaexampleapi.network.ApodApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApodApi(@Singleton retrofit: Retrofit): ApodApi {
        return retrofit.create(ApodApi::class.java)
    }
}
