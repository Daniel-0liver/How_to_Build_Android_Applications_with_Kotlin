package com.example.catdeployer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catdeployer.model.CatUiModel

@Composable
fun Employee(
    cat: CatUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable {
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
