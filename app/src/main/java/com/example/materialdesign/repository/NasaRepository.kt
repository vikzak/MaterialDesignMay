package com.example.materialdesign.repository

import com.example.materialdesign.repository.api.PictureOfTheDayResponse

interface NasaRepository {
    suspend fun pictuteOfTheDay(): PictureOfTheDayResponse
}