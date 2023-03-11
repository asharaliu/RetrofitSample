package com.example.retrofitsample.view.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.retrofitsample.model.Country
import org.junit.Rule
import org.junit.Test
class CountryListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun country_emptyCountry() {
        startCountries(arrayListOf())
        composeTestRule.onNodeWithText("No countries").assertIsDisplayed()
    }

    @Test
    fun country_withCountryList() {
        startCountries(arrayListOf(Country(
            "AGO",
            "https://upload.wikimedia.org/wikipedia/commons/9/9d/Flag_of_Angola.svg",
            "Angola",
            "AO"
        )))
        composeTestRule.onNodeWithTag("Country List").assertIsDisplayed()
    }

    /**
     * To call country list screen as per the input given
     */
    private fun startCountries(countryList: List<Country>) {
        composeTestRule.setContent {
            CountryListScreen(countryList = countryList, onItemClick = {})
        }
    }
}
