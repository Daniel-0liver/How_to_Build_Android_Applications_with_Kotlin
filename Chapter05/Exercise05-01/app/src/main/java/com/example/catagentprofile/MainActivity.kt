package com.example.catagentprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.catagentprofile.model.ImageResultData
import com.example.catagentprofile.ui.theme.CatAgentProfileTheme
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
            var serverResponse by remember { mutableStateOf("Loading...") }
            val scope = rememberCoroutineScope()
            CatAgentProfileTheme {
                LaunchedEffect(true) {
                    serverResponse = try {
                        val results = searchImages(1)
                        results.firstOrNull()?.imageUrl ?: "No results"
                    } catch (e: Exception) {
                        "Error: $e"
                    }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ServerResponse(
                        contents = serverResponse,
                        onClick = {
                            scope.launch {
                                serverResponse = try {
                                    val results = searchImages(1)
                                    results.firstOrNull()?.imageUrl ?: "No results"
                                } catch (e: Exception) {
                                    "Error: $e"
                                }
                            }
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private suspend fun searchImages(
        limit: Int,
    ): List<ImageResultData> = client.get(
        "https://api.thecatapi.com/v1/images/search"
    ) {
        url {
            parameter("limit", limit)
        }
    }.body()
}

@Composable
fun ServerResponse(
    contents: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = contents,
            contentDescription = "Cat Image",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = contents,
        )
        Button(
            onClick = onClick,
            modifier = Modifier.padding(top = 8.dp),
        ) {
            Text(text = "Update response")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ServerResponsePreview() {
    CatAgentProfileTheme {
        ServerResponse(
            contents = "Android",
            onClick = {}
        )
    }
}