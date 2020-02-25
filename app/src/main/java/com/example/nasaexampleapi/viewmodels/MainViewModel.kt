package com.example.nasaexampleapi.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.models.ApodImageState
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DefaultSubscriber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val nasaUseCase: NasaApodUseCase) : ViewModel() {

    var state = MutableLiveData<ApodImageState>(ApodImageState.RequestNotStartedYet)

    fun requestImageOfTheDay() {
        subscribeToFlowableAndPostValues(nasaUseCase.getNasaImageOfTheDay())
    }

    fun requestRandomDateImageOfTheDay() {
        subscribeToFlowableAndPostValues(nasaUseCase.getNasaRandomDayImageOfTheDay())
    }

    private fun subscribeToFlowableAndPostValues(flowable: Flowable<ApodImageState>) {
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