package com.example.nasaexampleapi.di.modules

import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.di.interfaces.ApplicationScope
import com.example.nasaexampleapi.domain.NasaApodRepository
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

    @Provides
    @ApplicationScope
    fun provideApodRepository(@ApplicationScope api: ApodApi): NasaApodRepository {
        return NasaApodRepository(api)
    }

    @Provides
    @ApplicationScope
    fun provideNasaApodUseCase(@ApplicationScope reposotory: NasaApodRepository): NasaApodUseCase {
        return NasaApodUseCase(reposotory)
    }
}