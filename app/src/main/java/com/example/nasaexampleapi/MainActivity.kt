package com.example.nasaexampleapi

import android.os.Bundle
import android.view.View
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
    }

    fun requestImageOfTheDay(v: View) {
        viewModel.requestImageOfTheDay().observe(this, observer)
    }

    fun requestRandomImageOfTheDay(v: View) {
        viewModel.requestRandomDateImageOfTheDay().observe(this, observer)
    }

    private fun handleResponse(response: ApodImageState?) {
        when (response) {
            is ApodImageState.Loading -> showLoading()
            is ApodImageState.Error -> showError(response.error)
            is ApodImageState.ImageResult -> showResponse(response.imageResponse)
            is ApodImageState.EmptyResult -> showEmptyResult()  //when result is video for example
        }
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun showEmptyResult(){
        progressBar.visibility = View.GONE
        Glide.with(this).load(R.drawable.no_image).into(ivImageOfTheDay)
        tvTitle.text = resources.getText(R.string.no_image_found)
        tvDescription.text = ""
    }

    private fun showResponse(imageResponse: ImageResponse) {
        progressBar.visibility = View.GONE
        tvTitle.text = imageResponse.title
        tvDescription.text = imageResponse.explanation
        Glide.with(this).load(imageResponse.url).into(ivImageOfTheDay)
    }

    private fun showError(error: Throwable) {
        progressBar.visibility = View.GONE
        tvTitle.text = resources.getText(R.string.error_occurred, error.message)
        tvDescription.text = ""
        Glide.with(this).load(R.drawable.no_image).into(ivImageOfTheDay)
    }
}
