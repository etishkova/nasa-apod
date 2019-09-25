package com.example.nasaexampleapi.viewmodels

import com.example.nasaexampleapi.business.NasaApodUseCase
import com.example.nasaexampleapi.di.ApplicationComponent
import com.example.nasaexampleapi.utilities.NasaApplication
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Test

class MainViewModelTest {

    @MockK
    val nasaUseCase: NasaApodUseCase = mockk()
    @MockK
    var appComponent: ApplicationComponent = mockk()
    @MockK
    var nasaApplication: NasaApplication = mockk()



    /*@Test
    fun `given main viewModel when image of the day requested then something happens` () {
        //given
        NasaApplication.appComponent = appComponent
        val viewModel = MainViewModel()
        viewModel.nasaUseCase = nasaUseCase
        every { appComponent.inject(nasaApplication) } just runs

        //when
        viewModel.requestImageOfTheDay()

        //then
        verify(exactly = 1) { nasaUseCase.getNasaImageOfTheDay() }

    }*/
}