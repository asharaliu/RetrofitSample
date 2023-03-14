package com.example.retrofitsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitsample.model.CountryList
import com.example.retrofitsample.model.ProvinceList
import com.example.retrofitsample.network.RequestState
import com.example.retrofitsample.network.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) :
    ViewModel() {
    private val _countryStateFlow =
        MutableStateFlow<RequestState<CountryList>>(RequestState.Loading)
    val countryListState = _countryStateFlow.asStateFlow()

    private val _provinceStateFlow =
        MutableStateFlow<RequestState<ProvinceList>>(RequestState.Loading)
    val provinceListState = _provinceStateFlow.asStateFlow()

    /**
     * API Call for the first screen(Countries list)
     */
    fun getCountryList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _countryStateFlow.value = RequestState.Success(countryRepository.getCountriesList())
            } catch (e: Exception) {
                _countryStateFlow.value = RequestState.Error(e)
            }
        }
    }

    /**
     * API Call for the second screen(Province list)
     */
    fun getProvinceList(iso2: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _provinceStateFlow.value = RequestState.Loading
                _provinceStateFlow.value =
                    RequestState.Success(countryRepository.getProvinceList(iso2))
            } catch (e: Exception) {
                _countryStateFlow.value = RequestState.Error(e)
            }
        }

    }
}