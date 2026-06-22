package com.example.recipeapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.recipeapp.DragAnchors.END
import com.example.recipeapp.DragAnchors.START
import com.example.recipeapp.model.RecipeUiModel
import kotlin.math.roundToInt

@Composable
fun Recipe(
    recipe: RecipeUiModel,
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
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(recipe.name)
            Text(recipe.description)
        }
    }
}

private enum class DragAnchors {
    START,
    END
}