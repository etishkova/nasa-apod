package com.example.nasaexampleapi.viewmodels

import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.utils.RxImmediateSchedulerRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get: Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK(relaxed = true)
    val nasaUseCase: NasaApodUseCase = mockk()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(nasaUseCase)
    }

    @Test
    fun `given main viewModel when first created then livedata state is request not started` () {

        val currentState = viewModel.state.value

        assert(currentState is ApodImageState.RequestNotStartedYet)
    }

    @Test
    fun `given main viewModel when requestImageOfTheDay called then useCase getNasaImageOfTheDay called` () {

        val imageResponseFlowable: Flowable<ApodImageState> = spyk()

        every { nasaUseCase.getNasaImageOfTheDay() } returns imageResponseFlowable

        viewModel.requestImageOfTheDay()

        verify(exactly = 1) { nasaUseCase.getNasaImageOfTheDay() }
    }

    @Test
    fun `given main viewModel when requestRandomDateImageOfTheDay called then useCase getNasaRandomDayImageOfTheDay called` () {

        val imageResponseFlowable: Flowable<ApodImageState> = spyk()

        every { nasaUseCase.getNasaRandomDayImageOfTheDay() } returns imageResponseFlowable

        viewModel.requestRandomDateImageOfTheDay()

        verify(exactly = 1) { nasaUseCase.getNasaRandomDayImageOfTheDay() }
    }
}