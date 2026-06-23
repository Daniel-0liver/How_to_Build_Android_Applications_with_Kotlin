package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.recipeapp.model.Flavor
import com.example.recipeapp.model.ListItemUiModel
import com.example.recipeapp.model.RecipeUiModel

@Composable
fun recipeListSavory(): MutableList<ListItemUiModel> {
    val list = remember {
        mutableStateListOf(
            ListItemUiModel.Title(
                title = "Savory"
            ),
            ListItemUiModel.Recipe(
                recipe = RecipeUiModel(
                    name = "Recipe Name",
                    description = "Recipe Description",
                    flavor = Flavor.SAVORY
                )
            ),
            ListItemUiModel.Recipe(
                recipe = RecipeUiModel(
                    name = "Recipe Name",
                    description = "Recipe Description",
                    flavor = Flavor.SAVORY
                )
            ),
            ListItemUiModel.Recipe(
                recipe = RecipeUiModel(
                    name = "Recipe Name",
                    description = "Recipe Description",
                    flavor = Flavor.SAVORY
                )
            )
        )
    }
    return list
}

@Composable
fun recipeListSweet(): MutableList<ListItemUiModel> {
    val list = remember {
        mutableStateListOf(
            ListItemUiModel.Title(
                title = "Sweet"
            ),
            ListItemUiModel.Recipe(
                recipe = RecipeUiModel(
                    name = "Recipe Name",
                    description = "Recipe Description",
                    flavor = Flavor.SWEET
                )
            ),
            ListItemUiModel.Recipe(
                recipe = RecipeUiModel(
                    name = "Recipe Name",
                    description = "Recipe Description",
                    flavor = Flavor.SWEET
                )
            ),
            ListItemUiModel.Recipe(
                recipe = RecipeUiModel(
                    name = "Recipe Name",
                    description = "Recipe Description",
                    flavor = Flavor.SWEET
                )
            )
        )
    }
    return list
}
