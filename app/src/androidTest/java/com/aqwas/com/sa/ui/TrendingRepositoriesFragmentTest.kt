package com.aqwas.com.sa.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aqwas.com.sa.MainActivity
import com.aqwas.com.sa.R
import com.aqwas.com.sa.model.TrendingRepositoriesModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TrendingRepositoriesFragmentTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = TrendingRepositoriesModel()


    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.rec_repos)).check(matches(isDisplayed()))
    }


}