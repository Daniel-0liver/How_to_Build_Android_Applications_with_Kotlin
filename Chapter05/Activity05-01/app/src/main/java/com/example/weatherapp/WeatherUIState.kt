package com.example.weatherapp

data class WeatherUIState(
    val mainWeather: String,
    val weatherDescription: String,
    val weatherIcon: String,
    val temperature: Double
)