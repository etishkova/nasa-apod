package com.example.nasaexampleapi.utilities

import com.example.nasaexampleapi.utils.Values.dateAfterNotIncluding
import com.example.nasaexampleapi.utils.Values.dateFormat
import org.junit.Test
import java.util.*

class HelpersTest {

    @Test
    fun `given random date in string is requested its not null or empty`() {
        val randomDate = Helpers.getRandomStringDateBeforeToday()
        assert(randomDate.isNotEmpty())
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
        val dateToCompare = dateFormat.parse(dateAfterNotIncluding)
        assert(dateToCompare!!.before(randomDate))
    }
}