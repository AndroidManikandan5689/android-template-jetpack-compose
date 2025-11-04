package com.chozhanaadu.androidtemplate.ui.screens.home

import com.chozhanaadu.androidtemplate.domain.model.Item

data class HomeUiState(
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)