package com.example.nasaexampleapi.utilities

import android.app.Activity
import android.app.Application
import android.app.Service
import com.example.nasaexampleapi.di.ApplicationComponent
import com.example.nasaexampleapi.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

class NasaApplication: Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    internal lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        injectApplication()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceDispatchingAndroidInjector
    }

    private fun injectApplication() {
        appComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}