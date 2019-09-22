package com.example.nasaexampleapi.models

import java.util.*

data class ImageRequest(
    var date: Date?,
    var hd: Boolean?
) {
    constructor() : this(null, null)
}