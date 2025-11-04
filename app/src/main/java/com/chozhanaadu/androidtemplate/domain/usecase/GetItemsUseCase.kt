package com.chozhanaadu.androidtemplate.domain.usecase

import com.chozhanaadu.androidtemplate.domain.model.Item
import com.chozhanaadu.androidtemplate.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    operator fun invoke(): Flow<List<Item>> = repository.getItems()
}