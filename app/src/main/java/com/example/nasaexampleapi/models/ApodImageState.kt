package com.example.nasaexampleapi.models

sealed class ApodImageState {

    object RequestNotStartedYet : ApodImageState()

    object EmptyResult : ApodImageState()

    object Loading : ApodImageState()

    class ImageResult(val imageResponse: ImageResponse) : ApodImageState()

    class Error(val error: Throwable) : ApodImageState()
}