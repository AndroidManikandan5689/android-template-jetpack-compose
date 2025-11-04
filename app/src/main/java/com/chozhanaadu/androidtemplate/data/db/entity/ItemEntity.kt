package com.chozhanaadu.androidtemplate.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)