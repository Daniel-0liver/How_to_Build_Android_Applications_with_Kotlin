package com.example.recipeapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.model.Flavor
import com.example.recipeapp.model.ListItemUiModel
import com.example.recipeapp.model.RecipeUiModel
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun RecipeScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val listState = rememberLazyListState()
            val recipeListSavory = remember {
                mutableStateListOf(
                    ListItemUiModel.Title("Savory"),
                    ListItemUiModel.Recipe(
                        recipe = RecipeUiModel(
                            name = "Recipe 1",
                            description = "Description 1",
                            flavor = Flavor.SAVORY
                        )
                    ),
                )
            }
            val recipeListSweet = remember {
                mutableStateListOf(
                    ListItemUiModel.Title("Sweet"),
                    ListItemUiModel.Recipe(
                        recipe = RecipeUiModel(
                            name = "Recipe 2",
                            description = "Description 2",
                            flavor = Flavor.SWEET
                        )
                    ),
                )
            }
            val context = LocalContext.current

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                items(
                    count = recipeListSavory.size,
                    key = { recipeListSavory[it].id }
                ) { index ->
                    when (val recipe = recipeListSavory[index]) {
                        is ListItemUiModel.Title -> {
                            Text(
                                text = recipe.title,
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                            )
                        }

                        is ListItemUiModel.Recipe -> {
                            Recipe(
                                recipe = recipe,
                                onClick = {
                                    Toast.makeText(
                                        context,
                                        recipe.recipe.description,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onSwipe = {
                                    recipeListSavory.removeAt(index)
                                },
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                }
                items(
                    count = recipeListSweet.size,
                    key = { recipeListSweet[it].id }
                ) { index ->
                    when (val recipe = recipeListSweet[index]) {
                        is ListItemUiModel.Title -> {
                            Text(
                                text = recipe.title,
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                            )
                        }

                        is ListItemUiModel.Recipe -> {
                            Recipe(
                                recipe = recipe,
                                onClick = {
                                    Toast.makeText(
                                        context,
                                        recipe.recipe.description,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onSwipe = {
                                    recipeListSweet.removeAt(index)
                                },
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            AddRecipeSection(recipeListSavory, context, recipeListSweet)
        }
    }
}

@Composable
private fun AddRecipeSection(
    recipeListSavory: SnapshotStateList<ListItemUiModel>,
    context: Context,
    recipeListSweet: SnapshotStateList<ListItemUiModel>
) {
    var recipeNameValue by remember { mutableStateOf("") }
    var recipeDescriptionValue by remember { mutableStateOf("") }
    OutlinedTextField(
        value = recipeNameValue,
        onValueChange = {
            recipeNameValue = it
        },
        label = { Text("Recipe Name") },
        maxLines = 1,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
    OutlinedTextField(
        value = recipeDescriptionValue,
        onValueChange = {
            recipeDescriptionValue = it
        },
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
            onClick = {
                if (recipeNameValue.isNotEmpty() && recipeDescriptionValue.isNotEmpty()) {
                    recipeListSavory.add(
                        ListItemUiModel.Recipe(
                            recipe = RecipeUiModel(
                                name = recipeNameValue,
                                description = recipeDescriptionValue,
                                flavor = Flavor.SAVORY
                            )
                        )
                    )
                    recipeNameValue = ""
                    recipeDescriptionValue = ""
                } else {
                    Toast.makeText(
                        context,
                        "Please enter a name and description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text("Add Savory")
        }
        Button(
            onClick = {
                if (recipeNameValue.isNotEmpty() && recipeDescriptionValue.isNotEmpty()) {
                    recipeListSweet.add(
                        ListItemUiModel.Recipe(
                            recipe = RecipeUiModel(
                                name = recipeNameValue,
                                description = recipeDescriptionValue,
                                flavor = Flavor.SWEET
                            )
                        )
                    )
                    recipeNameValue = ""
                    recipeDescriptionValue = ""
                } else {
                    Toast.makeText(
                        context,
                        "Please enter a name and description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text("Add Sweet")
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