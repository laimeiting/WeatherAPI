package com.medicare4u.androidtake_homeexercise

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {

    suspend fun getWeather(apiKey: String, city: String): WeatherResponse {
        return weatherApi.getWeather(apiKey, city)
    }
}
