package com.example.retrofitsample.network.repository

import com.example.retrofitsample.model.CountryList
import com.example.retrofitsample.model.ProvinceList
import com.example.retrofitsample.network.remote.ApiService

class CountryRepositoryImpl(private val apiService: ApiService):CountryRepository {
    override suspend fun getCountriesList(): CountryList {
        return apiService.getCountriesList()
    }

    override suspend fun getProvinceList(iso2: String?): ProvinceList {
        return apiService.getProvinceList(iso2)
    }
}