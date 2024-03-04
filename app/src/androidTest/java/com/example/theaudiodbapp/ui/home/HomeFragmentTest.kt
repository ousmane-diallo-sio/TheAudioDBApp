package com.example.theaudiodbapp.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.theaudiodbapp.MainActivity
import com.example.theaudiodbapp.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import tools.fastlane.screengrab.Screengrab


class HomeFragmentTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isHomeFragmentDisplayed() {
        onView(withId(R.id.nav_host_fragment_home_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_BottomNavigationWorking() {
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testTakeScreenshot() {
        Screengrab.screenshot("screenshot")
        onView(withId(R.id.ciSearchSearchFragment)).perform(ViewActions.click())
    }

}