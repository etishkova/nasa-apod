package com.example.nasaexampleapi.viewmodels

import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.di.ApplicationComponent
import com.example.nasaexampleapi.utilities.NasaApplication
import com.example.nasaexampleapi.utils.RxImmediateSchedulerRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule

class MainViewModelTest {
    @get: Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    val nasaUseCase: NasaApodUseCase = mockk()
    @MockK(relaxed = true)
    var appComponent: ApplicationComponent = mockk()

    private lateinit var viewModel: MainViewModel


    /*@Before
    fun setup() {
        mockkObject(NasaApplication)
        every { NasaApplication.appComponent } returns appComponent
        viewModel = MainViewModel()
        every { appComponent.inject(viewModel) } just runs

        every { viewModel.nasaUseCase } returns nasaUseCase
    }*/


    //TODO: fix this
    /*@Test
    fun `given main viewModel when first created then livedata state is request not started` () {

        val currentState = viewModel.state.value

        verify { currentState is ApodImageState.RequestNotStartedYet }
    }*/
}