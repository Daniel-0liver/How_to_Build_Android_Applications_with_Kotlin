package com.example.catdeployer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.catdeployer.DragAnchors.END
import com.example.catdeployer.DragAnchors.START
import com.example.catdeployer.model.CatUiModel
import com.example.catdeployer.model.ImageResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.math.roundToInt

@Composable
fun Cat(
    cat: CatUiModel,
    onClick: () -> Unit,
    onSwipe: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dragState = remember {
        AnchoredDraggableState(
            initialValue = START,
        )
    }

    LaunchedEffect(dragState.settledValue) {
        if (dragState.settledValue == END) {
            onSwipe()
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged { layoutSize ->
                dragState.updateAnchors(
                    DraggableAnchors {
                        START at 0f
                        END at layoutSize.width.toFloat()
                    }
                )
            }
            .offset {
                IntOffset(
                    x = dragState.requireOffset().roundToInt(),
                    y = 0
                )
            }
            .anchoredDraggable(
                state = dragState,
                orientation = Orientation.Horizontal
            )
            .clickable {
                onClick()
            }
    ) {
        if (cat.imageUrl.isNotEmpty()) {
            LoadedImage(
                imageUrl = cat.imageUrl,
                modifier = modifier.size(64.dp)
            )
        } else {
            Spacer(modifier = Modifier.size(64.dp))
        }
        Column {
            Text(text = cat.name)
            Text(text = cat.biography)
        }
    }
}

private val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

suspend fun getRandomCatImageUrl(): List<ImageResponse> {
    return client.get(
        urlString = "https://api.thecatapi.com/v1/images/search"
    ) {
        url {
            parameter("limit", 1)
        }
    }.body()
}


private enum class DragAnchors {
    START,
    END,
}
