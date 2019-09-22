package com.example.nasaexampleapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.models.ImageRequest
import com.example.nasaexampleapi.utilities.NasaApplication
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

class MainViewModel : ViewModel() {

    init {
        NasaApplication.appComponent.inject(this)
    }

    @Inject
    lateinit var nasaUseCase: NasaApodUseCase

    fun requestImageOfTheDay(): LiveData<ApodImageState> {
        val observableResponse = nasaUseCase.getNasaImageOfTheDay(ImageRequest())
        return convertToLiveData(observableResponse.toFlowable(BackpressureStrategy.BUFFER))
    }

    private fun convertToLiveData(flowable: Flowable<ApodImageState>): LiveData<ApodImageState> {
        return LiveDataReactiveStreams.fromPublisher(flowable)
    }
}