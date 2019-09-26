package com.example.nasaexampleapi

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.example.nasaexampleapi.models.ApodImageState
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.app.Activity
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.Stage
import com.example.nasaexampleapi.models.ImageResponse


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun onLaunchCheckInitialState() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.btnToday)).check(matches(isDisplayed()))
        onView(withId(R.id.btnRandom)).check(matches(isDisplayed()))
        onView(withId(R.id.ivImageOfTheDay)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun whenTodayPODButtonPressedProgressBarAppearsAndOtherTextValuesAreTheSame() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.btnToday)).perform(click())

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(R.string.title)))
    }

    @Test
    fun whenRandomPODButtonPressedProgressBarAppearsAndOtherTextValuesAreTheSame() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.btnRandom)).perform(click())

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(R.string.title)))
    }

    @Test
    fun whenRequestNotStartedStateIsObservedThenInitialStateIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        val activity = getActivityInstance() as MainActivity
        getInstrumentation().runOnMainSync {
            activity.handleResponse(ApodImageState.RequestNotStartedYet)
        }

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tvTitle)).check(matches(withText(R.string.title)))
        onView(withId(R.id.tvDescription)).check(matches(withText(R.string.empty_text)))
    }

    @Test
    fun whenRequestIsLoadingStateIsObservedThenLoadingStateIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        val activity = getActivityInstance() as MainActivity
        getInstrumentation().runOnMainSync {
            activity.handleResponse(ApodImageState.Loading)
        }

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(R.string.title)))
        onView(withId(R.id.tvDescription)).check(matches(withText(R.string.empty_text)))
    }

    @Test
    fun whenEmptyResultStateIsObservedThenEmptyResultStateIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        val activity = getActivityInstance() as MainActivity
        getInstrumentation().runOnMainSync {
            activity.handleResponse(ApodImageState.EmptyResult)
        }

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tvTitle)).check(matches(withText(R.string.no_image_found)))
        onView(withId(R.id.tvDescription)).check(matches(withText(R.string.empty_text)))
    }

    @Test
    fun whenErrorResultStateIsObservedThenErrorResultStateIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        val activity = getActivityInstance() as MainActivity
        getInstrumentation().runOnMainSync {
            activity.handleResponse(ApodImageState.Error(Throwable("")))
        }

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tvTitle)).check(matches(withText(R.string.error_occurred)))
        onView(withId(R.id.tvDescription)).check(matches(withText(R.string.empty_text)))
    }

    @Test
    fun whenValidResultStateIsObservedThenValidResultStateIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)
        val resultingImage = ImageResponse(
            "2016-9-10",
            "explanation",
            "title",
            "url"
        )

        val activity = getActivityInstance() as MainActivity
        getInstrumentation().runOnMainSync {
            activity.handleResponse(ApodImageState.ImageResult(resultingImage))
        }

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tvTitle)).check(matches(withText("title")))
        onView(withId(R.id.tvDescription)).check(matches(withText("explanation")))
    }

    private fun getActivityInstance(): Activity? {
        var currentActivity: Activity? = null
        getInstrumentation().runOnMainSync {
            val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            if (resumedActivities.iterator().hasNext()) {
                currentActivity = resumedActivities.iterator().next()
            }
        }

        return currentActivity
    }
}