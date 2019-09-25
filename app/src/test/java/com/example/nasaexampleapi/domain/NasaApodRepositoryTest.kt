package com.example.nasaexampleapi.domain

import com.example.nasaexampleapi.network.ApodApi
import com.example.nasaexampleapi.utils.Values.error
import com.example.nasaexampleapi.utils.Values.exampleDate
import com.example.nasaexampleapi.utils.Values.exampleImageResponse
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class NasaApodRepositoryTest {

    @MockK
    private val nasaApodApi: ApodApi = mockk()

    private lateinit var repository: NasaApodRepository

    @Before
    fun setup() {
        repository = NasaApodRepository(nasaApodApi)
    }

    @Test
    fun `given repository when pod is requested and no parameters given then api is called and correct response received` () {
        every { nasaApodApi.requestPictureOfTheDay(null) } returns Flowable.just(exampleImageResponse)

        val flowable = repository.requestPictureOfTheDay(null)

        verify(exactly = 1) { nasaApodApi.requestPictureOfTheDay(null) }
        flowable
            .test()
            .assertValue(exampleImageResponse)
    }

    @Test
    fun `given repository when pod is requested and date provided then api is called and correct response received` () {
        every { nasaApodApi.requestPictureOfTheDay(any()) } returns Flowable.just(exampleImageResponse)

        val flowable = repository.requestPictureOfTheDay(exampleDate)

        verify(exactly = 1) { nasaApodApi.requestPictureOfTheDay(exampleDate) }
        flowable
            .test()
            .assertValue(exampleImageResponse)
    }

    @Test
    fun `given repository when pod is requested and error is returned then api is called and method returns an error` () {
        every { nasaApodApi.requestPictureOfTheDay(any()) } returns Flowable.error(error)

        val flowable = repository.requestPictureOfTheDay(null)

        verify(exactly = 1) { nasaApodApi.requestPictureOfTheDay(null) }
        flowable
            .test()
            .assertError(error)
    }
}