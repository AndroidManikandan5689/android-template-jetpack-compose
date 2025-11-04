package com.chozhanaadu.androidtemplate.data.repository

import com.chozhanaadu.androidtemplate.data.api.ApiService
import com.chozhanaadu.androidtemplate.data.db.dao.ItemDao
import com.chozhanaadu.androidtemplate.data.mapper.toEntity
import com.chozhanaadu.androidtemplate.data.mapper.toModel
import com.chozhanaadu.androidtemplate.domain.model.Item
import com.chozhanaadu.androidtemplate.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val itemDao: ItemDao
) : ItemRepository {

    override fun getItems(): Flow<List<Item>> {
        return itemDao.getAllItems().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun refreshItems() {
        try {
            val items = apiService.getItems()
            itemDao.deleteAllItems()
            itemDao.insertItems(items.map { it.toEntity() })
        } catch (e: Exception) {
            throw e
        }
    }
}