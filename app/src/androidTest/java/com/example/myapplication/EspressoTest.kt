package com.example.myapplication

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.*

@RunWith(AndroidJUnit4::class)
@LargeTest
class EspressoTest {
    @get:Rule var activityRule = ActivityTestRule(MainActivity::class.java)
//    @Rule ActivityTestRule activityRule = new ActivityTestRule(MainActivity.class);
    @Test
    fun testInputMessage() {
        onView(withId(R.id.nameInput))
            .perform(typeText("espresso123"), closeSoftKeyboard())
        onView(withId(R.id.spinner))
            .perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("StarCraft")))
            .perform(click())
        onView(withId(R.id.button))
            .perform(click())

        onView(withId(R.id.textView1))
            .check(matches(withText("espresso123")))
        onView(withId(R.id.textView2))
            .check(matches(withText("StarCraft")))
    }
}