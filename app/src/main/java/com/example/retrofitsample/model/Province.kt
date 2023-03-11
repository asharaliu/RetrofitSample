package com.example.retrofitsample.model

data class ProvinceList(
    val error: Boolean,
    val mg: String,
    val data: ProvinceData)

data class ProvinceData(val states: List<Province>)
data class Province(
    val name: String?,
    val state_code: String?
)