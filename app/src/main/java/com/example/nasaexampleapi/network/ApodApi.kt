package com.example.nasaexampleapi.network

import com.example.nasaexampleapi.models.ImageResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApi {

    @GET("/planetary/apod")
    fun requestPictureOfTheDay(
        @Query("date") date: String?
    ): Observable<ImageResponse>
}