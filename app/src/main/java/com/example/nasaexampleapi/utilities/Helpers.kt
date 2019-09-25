package com.example.nasaexampleapi.utilities

import java.util.Calendar

import kotlin.random.Random

object Helpers {

    fun getRandomStringDateBeforeToday(): String {
        val today = Calendar.getInstance()
        val randomYear = Random.nextInt(2015, today.get(Calendar.YEAR) - 1)
        val randomMonth = Random.nextInt(0, 11)
        val randomDay = Random.nextInt(1, 28)
        return "$randomYear-$randomMonth-$randomDay"
    }
}