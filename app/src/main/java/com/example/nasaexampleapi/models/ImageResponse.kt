package com.example.nasaexampleapi.models

import java.util.Date

data class ImageResponse(
    var date: Date?,
    var media_type: String?,
    var explanation: String?,
    var hdurl: String?,
    var title: String?,
    var url: String?
) {
    fun isEmpty(): Boolean {
        return date == null
                && explanation.isNullOrEmpty()
                && hdurl.isNullOrEmpty()
                && title.isNullOrEmpty()
                && url.isNullOrEmpty()
    }
}