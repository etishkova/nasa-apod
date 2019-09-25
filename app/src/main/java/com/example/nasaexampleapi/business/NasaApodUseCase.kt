package com.example.nasaexampleapi.business

import androidx.annotation.VisibleForTesting
import com.example.nasaexampleapi.domain.NasaApodRepository
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.models.ImageRequest
import com.example.nasaexampleapi.utilities.Helpers
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Business logic will be here, for example data transformations, filtering, etc
 */
class NasaApodUseCase @Inject constructor(private val nasaApodRepository: NasaApodRepository) {

    fun getNasaRandomDayImageOfTheDay(): Flowable<ApodImageState> {
        val randomDate = Helpers.getRandomStringDateBeforeToday()
        return getNasaImageOfTheDay(ImageRequest(randomDate))
    }

    fun getNasaImageOfTheDay(): Flowable<ApodImageState> {
        return getNasaImageOfTheDay(ImageRequest())
    }

    private fun getNasaImageOfTheDay(imageRequest: ImageRequest?): Flowable<ApodImageState> {
        val response = nasaApodRepository.requestPictureOfTheDay(
            imageRequest?.date
        )

        val requestResult = response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error -> Timber.d("Error: $error") }

        return requestResult.map { imageDetailsResponse ->
            if (imageDetailsResponse.isEmpty()) {
                ApodImageState.EmptyResult
            } else ApodImageState.ImageResult(imageDetailsResponse)
        }
            .startWith(ApodImageState.Loading)
            .onErrorReturn { error ->
                ApodImageState.Error(error)
            }
    }
}
