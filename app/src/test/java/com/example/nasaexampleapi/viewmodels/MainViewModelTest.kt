package com.example.nasaexampleapi.viewmodels

import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.utils.RxImmediateSchedulerRule
import com.example.nasaexampleapi.utils.Values.exampleImageResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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

    /*@Test
    fun `given main viewModel when requestImageOfTheDay requested then useCase getNasaImageOfTheDay called` () {

        val imageResponseFlowable: Flowable<ApodImageState> = mockk()

        every { nasaUseCase.getNasaImageOfTheDay() } returns imageResponseFlowable
        every { imageResponseFlowable.observeOn(any()) } returns imageResponseFlowable
        every { imageResponseFlowable.subscribe(any()) } returns getDisposable()

        viewModel.requestImageOfTheDay()

        verify(exactly = 1) { nasaUseCase.getNasaImageOfTheDay() }
    }*/
}