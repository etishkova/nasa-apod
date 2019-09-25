package com.example.nasaexampleapi.utilities

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class HelpersTest {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val dateAfterNOtIncluding = "2014-12-31"

    @Test
    fun `given random date in string is requested its not null or empty`() {
        val randomDate = Helpers.getRandomStringDateBeforeToday()
        assert(!randomDate.isNullOrEmpty())
    }

    @Test
    fun `given random date in string is requested then returned date is in the past`() {
        val randomDateString = Helpers.getRandomStringDateBeforeToday()
        val randomDate = dateFormat.parse(randomDateString)
        assert(Date().after(randomDate))
    }

    @Test
    fun `given random date in string is requested then returned date is after december 2014`() {
        val randomDateString = Helpers.getRandomStringDateBeforeToday()
        val randomDate = dateFormat.parse(randomDateString)
        val dateToCompare = dateFormat.parse(dateAfterNOtIncluding)
        assert(dateToCompare!!.before(randomDate))
    }
}