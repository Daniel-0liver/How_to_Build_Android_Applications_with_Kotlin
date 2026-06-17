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
import com.example.catdeployer.DragAnchors.START
import com.example.catdeployer.model.CatUiModel
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
        if (dragState.settledValue == DragAnchors.END) {
            onSwipe()
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged { layoutSize ->
                dragState.updateAnchors(
                    DraggableAnchors {
                        DragAnchors.START at 0f
                        DragAnchors.END at layoutSize.width.toFloat()
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

private enum class DragAnchors {
    START,
    END,
}
