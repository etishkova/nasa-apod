package com.example.nasaexampleapi

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.models.ImageResponse
import com.example.nasaexampleapi.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    private var observer: Observer<ApodImageState> = Observer {
        handleResponse(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    fun requestImageOfTheDay(v: View) {
        viewModel.requestImageOfTheDay().observe(this, observer)
    }

    private fun handleResponse(response: ApodImageState?) {
        when (response) {
            is ApodImageState.Error -> showError(response.error)
            is ApodImageState.ImageResult -> showResponse(response.imageResponse)
        }
    }

    private fun showResponse(imageResponse: ImageResponse) {
        tvText.text = imageResponse.title
    }

    private fun showError(error: Throwable) {
        Timber.d("ERROR: $error")
        tvText.text = error.message
    }
}
