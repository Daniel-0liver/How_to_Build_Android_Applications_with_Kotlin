package com.example.catdeployer.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CatUiModel(
    val gender: Gender,
    val name: String,
    val biography: String,
    val imageUrl: String,
)

@Serializable
data class ImageResponse(
    @SerialName("url") val imageUrl: String
)
