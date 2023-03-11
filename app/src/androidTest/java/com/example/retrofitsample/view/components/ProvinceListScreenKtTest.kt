package com.example.retrofitsample.view.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.retrofitsample.model.Province
import org.junit.Rule
import org.junit.Test

class ProvinceListScreenTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun country_emptyCountry() {
        startProvinces(arrayListOf())
        composeTestRule.onNodeWithText("No Provinces").assertIsDisplayed()
    }

    @Test
    fun country_withCountryList() {
        startProvinces(arrayListOf(Province("Abia State", "AB")))
        composeTestRule.onNodeWithTag("Province List").assertIsDisplayed()
    }

    /**
     * To call Provinces list screen as per the input given
     */
    private fun startProvinces(countryList: List<Province>) {
        composeTestRule.setContent {
            ProvinceListScreen(provinceList = countryList)
        }
    }
}