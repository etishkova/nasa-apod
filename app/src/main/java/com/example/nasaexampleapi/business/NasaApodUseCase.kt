package com.example.nasaexampleapi.business

import com.example.nasaexampleapi.domain.NasaApodRepository
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.models.ImageRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Business logic will be here, for example data transformations, filtering, etc
 */
class NasaApodUseCase @Inject constructor(private val nasaApodRepository: NasaApodRepository) {

    fun getNasaImageOfTheDay(request: ImageRequest): Observable<ApodImageState> {
        //TODO: add date conversion to YYYY-MM-DD format
        val response = nasaApodRepository.requestPictureOfTheDay(null, null)

        val requestResult = response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error -> Timber.d("Error: $error") }

        return requestResult.map { imageDetailsResponse ->
            if (imageDetailsResponse.isEmpty()) {
                ApodImageState.EmptyResult
            } else ApodImageState.ImageResult(imageDetailsResponse)
        }
            .startWith(ApodImageState.RequestNotStartedYet)
            .onErrorReturn { error ->
                ApodImageState.Error(error)
            }
    }
}