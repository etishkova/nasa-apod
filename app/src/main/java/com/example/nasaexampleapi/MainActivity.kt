package com.example.nasaexampleapi

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.models.ImageResponse
import com.example.nasaexampleapi.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    private var observer: Observer<ApodImageState> = Observer {
        handleResponse(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.state.observe(this, observer)
    }

    fun requestImageOfTheDay(v: View) {
        viewModel.requestImageOfTheDay()
    }

    fun requestRandomImageOfTheDay(v: View) {
        viewModel.requestRandomDateImageOfTheDay()
    }

    @VisibleForTesting
    internal fun handleResponse(response: ApodImageState?) {
        when (response) {
            is ApodImageState.RequestNotStartedYet -> showRequestNotStarted()
            is ApodImageState.Loading -> showLoading()
            is ApodImageState.Error -> showError(response.error)
            is ApodImageState.ImageResult -> showResponse(response.imageResponse)
            is ApodImageState.EmptyResult -> showEmptyResult()
        }
    }

    private fun showRequestNotStarted(){
        progressBar.visibility = View.GONE
        tvTitle.text = resources.getText(R.string.title)
        tvDescription.text = resources.getText(R.string.empty_text)
        Glide.with(this).load(R.drawable.placeholder).into(ivImageOfTheDay)
    }

    private fun showEmptyResult(){
        progressBar.visibility = View.GONE
        Glide.with(this).load(R.drawable.no_image).into(ivImageOfTheDay)
        tvTitle.text = resources.getText(R.string.no_image_found)
        tvDescription.text = resources.getText(R.string.empty_text)
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun showResponse(imageResponse: ImageResponse) {
        progressBar.visibility = View.GONE
        tvTitle.text = imageResponse.title
        tvDescription.text = imageResponse.explanation
        Glide.with(this).load(imageResponse.url).into(ivImageOfTheDay)
    }

    private fun showError(error: Throwable) {
        progressBar.visibility = View.GONE
        tvTitle.text = resources.getText(R.string.error_occurred)
        tvDescription.text = resources.getText(R.string.empty_text)
        Glide.with(this).load(R.drawable.no_image).into(ivImageOfTheDay)
    }
}
