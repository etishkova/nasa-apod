package com.example.nasaexampleapi.models

data class ImageRequest(
    var date: String?  //date in format YYYY-MM-DD
) {
    constructor() : this(null)
}