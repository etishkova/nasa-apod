package com.example.nasaexampleapi.models

data class ImageResponse(
    var date: String?,
    var media_type: String?,
    var explanation: String?,
    var hdurl: String?,
    var title: String?,
    var url: String?
) {
    constructor(): this(
        null,
        null,
        null,
        null,
        null,
        null
        )

    constructor(
        date: String,
        explanation: String,
        title: String,
        url: String
        ) : this(
        date,
        null,
        explanation,
        null,
        title,
        url
    )

    fun isEmpty(): Boolean {
        return date == null
                && explanation.isNullOrEmpty()
                && hdurl.isNullOrEmpty()
                && title.isNullOrEmpty()
                && url.isNullOrEmpty()
    }
}