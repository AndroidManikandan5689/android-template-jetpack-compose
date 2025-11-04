package com.chozhanaadu.androidtemplate.domain.usecase

import com.chozhanaadu.androidtemplate.domain.repository.ItemRepository
import javax.inject.Inject

class RefreshItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    suspend operator fun invoke() = repository.refreshItems()
}