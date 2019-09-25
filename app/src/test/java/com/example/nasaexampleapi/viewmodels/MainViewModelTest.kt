package com.example.nasaexampleapi.viewmodels

import androidx.lifecycle.ViewModel
import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.di.ApplicationComponent
import com.example.nasaexampleapi.models.ApodImageState
import com.example.nasaexampleapi.utilities.Helpers
import com.example.nasaexampleapi.utilities.NasaApplication
import com.example.nasaexampleapi.utils.RxImmediateSchedulerRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get: Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    val nasaUseCase: NasaApodUseCase = mockk()
    @MockK(relaxed = true)
    var appComponent: ApplicationComponent = mockk()

    private lateinit var viewModel: MainViewModel


    @Before
    fun setup() {
        mockkObject(NasaApplication)
        every { NasaApplication.appComponent } returns appComponent
        viewModel = MainViewModel()
        every { appComponent.inject(viewModel) } just runs

        every { viewModel.nasaUseCase } returns nasaUseCase
    }


    /*@Test
    fun `given main viewModel when image of the day requested then something happens` () {

        verify { viewModel.state.value is ApodImageState.RequestNotStartedYet }
        //when

        //then
        //verify(exactly = 1) { nasaUseCase.getNasaImageOfTheDay() }

    }*/
}