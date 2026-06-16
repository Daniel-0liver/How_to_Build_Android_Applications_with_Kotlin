package com.example.catdeployer.model

sealed interface ListItemUiModel {
    data class Cat(
        val cat: CatUiModel
    ) : ListItemUiModel

    data class Title(
        val title: String
    ) : ListItemUiModel
}
