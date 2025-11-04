package com.chozhanaadu.androidtemplate.data.mapper

import com.chozhanaadu.androidtemplate.data.db.entity.ItemEntity
import com.chozhanaadu.androidtemplate.data.model.ItemDto
import com.chozhanaadu.androidtemplate.domain.model.Item

fun ItemDto.toEntity() = ItemEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl
)

fun ItemEntity.toModel() = Item(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl
)

fun ItemDto.toModel() = Item(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl
)