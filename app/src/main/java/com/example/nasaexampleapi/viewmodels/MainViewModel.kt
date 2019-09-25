package com.example.nasaexampleapi.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.utilities.NasaApplication
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DefaultSubscriber
import javax.inject.Inject

class MainViewModel : ViewModel() {

    var state = MutableLiveData<ApodImageState>(ApodImageState.RequestNotStartedYet)

    @Inject
    lateinit var nasaUseCase: NasaApodUseCase

    init {
        NasaApplication.appComponent.inject(this)
    }

    fun requestImageOfTheDay() {
        subscribeToFlowableAndPostValues(nasaUseCase.getNasaImageOfTheDay())
    }

    fun requestRandomDateImageOfTheDay() {
        subscribeToFlowableAndPostValues(nasaUseCase.getNasaRandomDayImageOfTheDay())
    }

    @VisibleForTesting
    private fun subscribeToFlowableAndPostValues(flowable: Flowable<ApodImageState>){
        flowable
            .observeOn(Schedulers.io())
            .subscribe(object: DefaultSubscriber<ApodImageState>() {

                override fun onNext(imageState: ApodImageState?) {
                    state.postValue(imageState)
                }

                override fun onComplete() {}

                override fun onError(error: Throwable) {
                    state.postValue(ApodImageState.Error(error))
                }
            })
    }
}