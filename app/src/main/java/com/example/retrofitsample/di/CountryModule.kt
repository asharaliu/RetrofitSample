package com.example.retrofitsample.di

import com.example.retrofitsample.network.remote.ApiService
import com.example.retrofitsample.network.repository.CountryRepository
import com.example.retrofitsample.network.repository.CountryRepositoryImpl
import com.example.retrofitsample.util.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryModule {

    @Provides
    @Singleton
    fun provideCountryApiService():ApiService{
        return Retrofit.Builder().baseUrl(Const.COUNTRY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(apiService: ApiService):CountryRepository{
        return CountryRepositoryImpl(apiService)
    }
}