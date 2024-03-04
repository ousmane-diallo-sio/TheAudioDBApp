package com.example.theaudiodbapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Assert.*
import org.junit.Test
import tools.fastlane.screengrab.Screengrab

class MainActivityTest {
    @Test
    fun test_isMainActivityDisplayed() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.cl_root_main)).check(matches(isDisplayed()))
    }

    @Test
    fun testTakeScreenshot() {
        Screengrab.screenshot("main_activity_screenshot")
        //Screengrab.screenshot("before_button_click")
        //onView(withId(android.R.id.home)).perform(ViewActions.click())
        //Screengrab.screenshot("after_button_click")
    }
}