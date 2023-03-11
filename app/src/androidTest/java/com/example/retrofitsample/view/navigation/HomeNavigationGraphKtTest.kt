package com.example.retrofitsample.view.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.retrofitsample.network.repository.CountryRepository
import com.example.retrofitsample.util.Const
import com.example.retrofitsample.viewmodel.CountryViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var navController: TestNavHostController

    @Inject
    lateinit var countryRepo: CountryRepository

    @Before
    fun setupAppNavHost() {
        hiltRule.inject()
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            HomeNavigationRoute(CountryViewModel(countryRepo),navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithContentDescription("Country Screen")
            .assertIsDisplayed()
    }

    @Test
    fun appNavHost_clickAllCountries_navigateToProvince() {
        composeTestRule.waitUntil(3000){
            composeTestRule
                .onAllNodesWithTag("Country List Item")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Algeria").performClick()
        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "${Screen.Provinces.route}/{${Const.ARG_COUNTRY_ID}}")
    }
}