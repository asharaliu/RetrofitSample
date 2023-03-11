package com.example.retrofitsample.network.repository

import com.example.retrofitsample.model.CountryList
import com.example.retrofitsample.model.ProvinceList

interface CountryRepository {
    suspend fun getCountriesList(): CountryList
    suspend fun getProvinceList(iso2: String?): ProvinceList
}