package com.retailcloudandroid.retailcloudmachinetestandroid.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase.GetCartItemsUseCase
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase.GetCartSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val getCartSummaryUseCase: GetCartSummaryUseCase
) : ViewModel() {

    val uiState: StateFlow<CartUiState> = getCartItemsUseCase()
        .map { items ->
            if (items.isEmpty()) {
                CartUiState.Empty
            } else {
                CartUiState.Success(
                    items = items,
                    summary = getCartSummaryUseCase(items)
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CartUiState.Loading
        )
}
