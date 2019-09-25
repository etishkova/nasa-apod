package com.example.nasaexampleapi.business

import com.example.nasaexampleapi.domain.NasaApodRepository
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.utilities.Helpers
import com.example.nasaexampleapi.utilities.Helpers.getRandomStringDateBeforeToday
import com.example.nasaexampleapi.utils.Values.emptyImageResponse
import com.example.nasaexampleapi.utils.Values.error
import com.example.nasaexampleapi.utils.Values.exampleDate
import com.example.nasaexampleapi.utils.Values.exampleImageResponse
import com.example.nasaexampleapi.utils.Values.todayImageResponse
import com.example.nasaexampleapi.utils.RxImmediateSchedulerRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NasaApodUseCaseTest {
    @get: Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    private val repository: NasaApodRepository = mockk()

    private lateinit var useCase: NasaApodUseCase

    @Before
    fun setup() {
        useCase = NasaApodUseCase(repository)
    }

    @Test
    fun `given usecase when valid pod is requested and available then repository called and loading and image states emitted` () {
        every { repository.requestPictureOfTheDay(null) } returns Flowable.just(todayImageResponse)

        val flowable = useCase.getNasaImageOfTheDay()

        verify(exactly = 1) { repository.requestPictureOfTheDay(null) }

        flowable
            .test()
            .assertNoErrors()
            .assertValueCount(2)
            .assertNever(ApodImageState.EmptyResult)
            .assertValueAt(0, ApodImageState.Loading)
            .assertValueAt(1) { isImageResult(it) }
    }

    @Test
    fun `given usecase when pod is requested and empty result received then repository called and loading and empty states emitted` () {
        every { repository.requestPictureOfTheDay(null) } returns Flowable.just(emptyImageResponse)

        val flowable = useCase.getNasaImageOfTheDay()

        verify(exactly = 1) { repository.requestPictureOfTheDay(null) }

        flowable
            .test()
            .assertNoErrors()
            .assertValueCount(2)
            .assertValueAt(0, ApodImageState.Loading)
            .assertValueAt(1, ApodImageState.EmptyResult)
    }

    @Test
    fun `given usecase when pod is requested and error received then repository called and loading and error states emitted` () {
        every { repository.requestPictureOfTheDay(null) } returns Flowable.error(error)

        val flowable = useCase.getNasaImageOfTheDay()

        verify(exactly = 1) { repository.requestPictureOfTheDay(null) }

        flowable
            .test()
            .assertNoErrors()
            .assertValueCount(2)
            .assertValueAt(0, ApodImageState.Loading)
            .assertValueAt(1) { isError(it) }
    }

    @Test
    fun `given usecase when valid random pod is requested and available then repository called and loading and image states emitted` () {
        mockkObject(Helpers)
        every { repository.requestPictureOfTheDay(exampleDate) } returns Flowable.just(exampleImageResponse)
        every { getRandomStringDateBeforeToday() } returns exampleDate

        val flowable = useCase.getNasaRandomDayImageOfTheDay()

        verify(exactly = 1) { repository.requestPictureOfTheDay(exampleDate) }

        flowable
            .test()
            .assertNoErrors()
            .assertValueCount(2)
            .assertNever(ApodImageState.EmptyResult)
            .assertValueAt(0, ApodImageState.Loading)
            .assertValueAt(1) { isImageResult(it) }
    }

    @Test
    fun `given usecase when random pod is requested and empty result received then repository called and loading and empty states emitted` () {
        mockkObject(Helpers)
        every { getRandomStringDateBeforeToday() } returns exampleDate
        every { repository.requestPictureOfTheDay(exampleDate) } returns Flowable.just(emptyImageResponse)

        val flowable = useCase.getNasaRandomDayImageOfTheDay()

        verify(exactly = 1) { repository.requestPictureOfTheDay(exampleDate) }

        flowable
            .test()
            .assertNoErrors()
            .assertValueCount(2)
            .assertValueAt(0, ApodImageState.Loading)
            .assertValueAt(1, ApodImageState.EmptyResult)
    }

    @Test
    fun `given usecase when random pod is requested and error received then repository called and loading and error states emitted` () {
        mockkObject(Helpers)
        every { getRandomStringDateBeforeToday() } returns exampleDate
        every { repository.requestPictureOfTheDay(exampleDate) } returns Flowable.error(error)

        val flowable = useCase.getNasaRandomDayImageOfTheDay()

        verify(exactly = 1) { repository.requestPictureOfTheDay(exampleDate) }

        flowable
            .test()
            .assertNoErrors()
            .assertValueCount(2)
            .assertValueAt(0, ApodImageState.Loading)
            .assertValueAt(1) { isError(it) }
    }

    private fun isImageResult(value: ApodImageState): Boolean {
        return value is ApodImageState.ImageResult
    }

    private fun isError(value: ApodImageState): Boolean {
        return value is ApodImageState.Error
    }
}
