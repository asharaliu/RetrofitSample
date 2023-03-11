package com.example.retrofitsample.network.remote

import com.example.retrofitsample.model.CountryList
import com.example.retrofitsample.model.ProvinceList
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("flag/images")
    suspend fun getCountriesList(): CountryList

    @FormUrlEncoded
    @POST("states")
    suspend fun getProvinceList(@Field("iso2") iso2: String?): ProvinceList
}