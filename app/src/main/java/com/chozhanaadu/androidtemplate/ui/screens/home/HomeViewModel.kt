package com.chozhanaadu.androidtemplate.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chozhanaadu.androidtemplate.domain.usecase.GetItemsUseCase
import com.chozhanaadu.androidtemplate.domain.usecase.RefreshItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val refreshItemsUseCase: RefreshItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeItems()
        refreshItems()
    }

    private fun observeItems() {
        viewModelScope.launch {
            getItemsUseCase()
                .catch { exception ->
                    _uiState.update { it.copy(error = exception.message) }
                }
                .collect { items ->
                    _uiState.update { it.copy(
                        items = items,
                        error = null
                    ) }
                }
        }
    }

    fun refreshItems() {
        viewModelScope.launch {
            _uiState.update { it.copy(
                isLoading = true,
                error = null
            ) }
            
            try {
                refreshItemsUseCase()
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    isLoading = false,
                    error = e.message
                ) }
            }
        }
    }
}