package com.example.nasaexampleapi.domain

import com.example.nasaexampleapi.models.ImageResponse
import com.example.nasaexampleapi.network.ApodApi
import io.reactivex.Observable
import javax.inject.Inject

/**
 * This class will be responsible for deciding whether data will be received from the
 * api or from local storage if there is anything available. At first iteration it'll only
 * retrieve from API
 */
class NasaApodRepository @Inject constructor(private val nasaApodApi: ApodApi) {

    fun requestPictureOfTheDay(
        date: String?,
        hd: String?
    ): Observable<ImageResponse> {
        return nasaApodApi.requestPictureOfTheDay(date, hd)
    }
}