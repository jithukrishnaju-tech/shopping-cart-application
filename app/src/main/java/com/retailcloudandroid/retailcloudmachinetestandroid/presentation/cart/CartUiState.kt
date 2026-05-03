package com.retailcloudandroid.retailcloudmachinetestandroid.presentation.cart

import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartItem
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartSummary

sealed class CartUiState {
    data object Loading : CartUiState()
    data class Success(val items: List<CartItem>, val summary: CartSummary) : CartUiState()
    data object Empty : CartUiState()
    data class Error(val message: String) : CartUiState()
}
