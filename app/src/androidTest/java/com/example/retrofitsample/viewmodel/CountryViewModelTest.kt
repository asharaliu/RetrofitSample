package com.example.retrofitsample.viewmodel

import com.example.retrofitsample.network.RequestState
import com.example.retrofitsample.network.repository.CountryRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CountryViewModelTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var countryRepo: CountryRepository

    @Before
    fun setupDi() {
        hiltRule.inject()
    }

    @Test
    fun country_countryListCall() {
        val countryViewModel = CountryViewModel(countryRepo)
        countryViewModel.getCountryList()
        assert(countryViewModel.countryListState.value is RequestState.Loading)
    }

    @Test
    fun province_provinceListCall() {
        val countryViewModel = CountryViewModel(countryRepo)
        countryViewModel.getProvinceList("IN")
        assert(countryViewModel.provinceListState.value is RequestState.Loading)
    }
}