package com.example.weatherapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val weather: List<WeatherData>,
    val main: Main
)

@Serializable
data class WeatherData(
    @SerialName("main") val mainWeather: String,
    @SerialName("description") val weatherDescription: String,
    @SerialName("icon") val weatherIcon: String
)

@Serializable
data class Main(
    val temp: Double
)
