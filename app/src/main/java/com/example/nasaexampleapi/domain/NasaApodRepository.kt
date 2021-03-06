package com.example.nasaexampleapi.domain

import com.example.nasaexampleapi.models.ImageResponse
import com.example.nasaexampleapi.network.ApodApi
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * This class will be responsible for deciding whether data will be received from the
 * api or from local storage if there is anything available. At the moment only API requests
 */
class NasaApodRepository @Inject constructor(private val nasaApodApi: ApodApi) {

    fun requestPictureOfTheDay(
        date: String?
    ): Flowable<ImageResponse> {
        return nasaApodApi.requestPictureOfTheDay(date)
    }
}