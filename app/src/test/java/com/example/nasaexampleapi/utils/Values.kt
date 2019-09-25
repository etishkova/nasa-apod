package com.example.nasaexampleapi.utils

import com.example.nasaexampleapi.models.ImageResponse

object Values {

    const val exampleDate = "2017-6-3"
    val exampleImageResponse = ImageResponse(
        exampleDate,
        "explanation",
        "title",
        "url"
    )

    val todayImageResponse = ImageResponse(
        "2019-9-10",
        "explanation",
        "title",
        "url"
    )
    val emptyImageResponse = ImageResponse()
    val error = Throwable()
}