package com.example.retrofitsample.model

data class CountryList(
    val error: Boolean,
    val mg: String,
    val data: List<Country>)

data class Country(
    val iso3: String?,
    val flag: String?,
    val name: String?,
    val iso2: String?
)