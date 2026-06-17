package com.example.catdeployer.model

import java.util.UUID


sealed interface ListItemUiModel {
    val id: String
        get() = UUID.randomUUID().toString()

    data class Cat(
        val cat: CatUiModel
    ) : ListItemUiModel

    data class Title(
        val title: String
    ) : ListItemUiModel
}
