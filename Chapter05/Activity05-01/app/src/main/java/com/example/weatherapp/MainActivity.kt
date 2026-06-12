package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.ui.theme.WeatherAppTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var uiState by remember {
                mutableStateOf(
                    WeatherUIState(
                        mainWeather = "Loading...",
                        weatherDescription = "Loading...",
                        weatherIcon = "01d",
                        temperature = 0.0
                    )
                )
            }
            var value by remember { mutableStateOf("") }
            val scope = rememberCoroutineScope()
            WeatherAppTheme {
                LaunchedEffect(true) {
                    try {
                        uiState = fetchWeatherUIState("London")
                    } catch (e: Exception) {
                        uiState = WeatherUIState(
                            mainWeather = "Error",
                            weatherDescription = e.message ?: "Unknown error",
                            weatherIcon = "01d",
                            temperature = 0.0
                        )
                    }
                }
                WeatherScreen(
                    currentWeather = uiState.mainWeather.trim(),
                    weatherInfo = uiState.weatherDescription.trim(),
                    weatherIcon = "https://openweathermap.org/img/wn/${uiState.weatherIcon.trim()}@2x.png",
                    temperature = uiState.temperature,
                    value = value,
                    onValueChange = {
                        value = it
                    },
                    onClick = {
                        scope.launch {
                            uiState = try {
                                fetchWeatherUIState(value)
                            } catch (e: Exception) {
                                WeatherUIState(
                                    mainWeather = "Error",
                                    weatherDescription = e.message ?: "Unknown error",
                                    weatherIcon = "01d",
                                    temperature = 0.0
                                )
                            }
                        }
                    }
                )
            }
        }
    }

    private suspend fun fetchWeatherUIState(city: String): WeatherUIState {
        val response: WeatherResponse = client.get(
            "https://api.openweathermap.org/data/2.5/weather"
        ) {
            url {
                parameter("q", city)
                parameter("appid", BuildConfig.WEATHER_API_KEY)
                parameter("units", "metric")
            }
        }.body()

        val weather = response.weather.first()
        return WeatherUIState(
            mainWeather = weather.mainWeather,
            weatherDescription = weather.weatherDescription,
            weatherIcon = weather.weatherIcon,
            temperature = response.main.temp
        )
    }
}

@Composable
fun WeatherScreen(
    currentWeather: String,
    weatherInfo: String,
    weatherIcon: String,
    temperature: Double,
    value: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text("City") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                maxLines = 1,
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = onClick,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Get Weather")
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = currentWeather)
                LoadedImage(
                    imageUrl = weatherIcon,
                    modifier = Modifier.padding(start = 300.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = weatherInfo)
                Text(
                    text = "Temperature: $temperature°C",
                    modifier = Modifier.padding(start = 100.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun WeatherScreenPreview() {
    WeatherAppTheme {
        WeatherScreen(
            currentWeather = "Rain",
            weatherInfo = "Heavy intensity rain",
            weatherIcon = "",
            value = "",
            onValueChange = {},
            onClick = {},
            temperature = 0.0
        )
    }
}
