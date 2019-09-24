package com.example.nasaexampleapi

import com.example.nasaexampleapi.utilities.Helpers
import org.junit.Test

class HelpersTest {

    @Test
    fun nonNullOrEmptyReturn(){
        val randomDate = Helpers.getRandomStringDateBeforeToday()
        assert(!randomDate.isNullOrEmpty())
    }
}