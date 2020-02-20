package com.example.nasaexampleapi.di

import android.app.Application
import com.example.nasaexampleapi.BaseActivity
import com.example.nasaexampleapi.di.modules.ApiModule
import com.example.nasaexampleapi.di.modules.NetworkModule
import com.example.nasaexampleapi.utilities.NasaApplication
import com.example.nasaexampleapi.viewmodels.MainViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    NetworkModule::class,
    ApiModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: NasaApplication)

    fun inject(mainActivity: BaseActivity)

    fun inject(mainViewModel: MainViewModel)
}