package com.example.materialdesign.domain

import com.example.materialdesign.api.NasaApi
import com.example.materialdesign.api.PictureOfTheDayResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NasaRepositoryImplementation : NasaRepository {

    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.nasa.gov/")
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
            .build()
        )
        .build()
        .create(NasaApi::class.java)

    override suspend fun pictuteOfTheDay(): PictureOfTheDayResponse = api.pictureOfTheDay("yJ4oJRXWsUZu62R3i8wrLb0yh5km66z4g3NXlDW2")
}