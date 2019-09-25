package com.example.nasaexampleapi.utils

import com.example.nasaexampleapi.models.ImageResponse
import java.text.SimpleDateFormat

object Values {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    const val dateAfterNotIncluding = "2014-12-31"
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