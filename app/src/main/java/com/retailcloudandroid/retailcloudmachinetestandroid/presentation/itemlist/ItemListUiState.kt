package com.retailcloudandroid.retailcloudmachinetestandroid.presentation.itemlist

import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.Item

sealed class ItemListUiState {
    data object Idle : ItemListUiState()
    data object Loading : ItemListUiState()
    data class Success(val items: List<Item>) : ItemListUiState()
    data class Error(val message: String, val isNetworkError: Boolean) : ItemListUiState()
}
