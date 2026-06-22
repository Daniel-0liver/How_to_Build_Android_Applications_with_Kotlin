package com.example.recipeapp.model

sealed interface ListItemUiModel {
    val id: String
        get() = java.util.UUID.randomUUID().toString()

    data class Recipe(
        val recipe: RecipeUiModel
    ) : ListItemUiModel

    data class Title(
        val title: String
    ) : ListItemUiModel
}
