package com.chozhanaadu.androidtemplate.domain.repository

import com.chozhanaadu.androidtemplate.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
    suspend fun refreshItems()
}