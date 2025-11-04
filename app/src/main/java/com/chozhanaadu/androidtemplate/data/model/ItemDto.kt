package com.chozhanaadu.androidtemplate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)