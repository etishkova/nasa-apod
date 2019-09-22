package com.example.nasaexampleapi

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nasaexampleapi.utilities.NasaApplication

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        //AndroidInjection.inject(this)
        NasaApplication.appComponent.inject(this)
    }
}