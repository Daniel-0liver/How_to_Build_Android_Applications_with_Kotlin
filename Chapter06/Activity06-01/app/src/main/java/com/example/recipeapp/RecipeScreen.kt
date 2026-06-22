package com.example.recipeapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) { }

            OutlinedTextField(
                value = "",
                onValueChange = { /* TODO */ },
                label = { Text("Recipe Name") },
                maxLines = 1,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = "",
                onValueChange = { /* TODO */ },
                label = { Text("Recipe Description") },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Text("Add Savory")
                }
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Text("Add Sweet")
                }
            }
        }
    }
}

@Preview
@Composable
private fun RecipeScreenPreview() {
    RecipeAppTheme {
        RecipeScreen()
    }
}