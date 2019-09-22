package com.example.nasaexampleapi.models

data class ImageResponse(
    //var date: String?,
    var explanation: String?,
    var hdurl: String?,
    var title: String?,
    var url: String?
) {
    fun isEmpty(): Boolean {
        return /*date == null
                &&*/ explanation.isNullOrEmpty()
                && hdurl.isNullOrEmpty()
                && title.isNullOrEmpty()
                && url.isNullOrEmpty()
    }
}