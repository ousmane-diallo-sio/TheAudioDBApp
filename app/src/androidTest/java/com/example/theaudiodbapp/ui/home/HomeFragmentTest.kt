package com.example.theaudiodbapp.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.theaudiodbapp.MainActivity
import com.example.theaudiodbapp.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class HomeFragmentTest {

    @get: Rule
    val activityScenario = ActivityScenario.launch(MainActivity::class.java)

    @Test
    fun test_isHomeFragmentDisplayed() {
        onView(withId(R.id.nav_host_fragment_home_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_BottomNavigationWorking() {
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
    }

}