package com.example.materialdesign.repository.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("planetary/apod")
    suspend fun pictureOfTheDay(
        @Query("api_key") key: String,
        @Query("date") date: String
    ): PictureOfTheDayResponse
}