package com.retailcloudandroid.retailcloudmachinetestandroid.presentation.itemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartItem
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartSummary
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.Item
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase.AddToCartUseCase
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase.GetCartItemsUseCase
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase.GetCartSummaryUseCase
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase.GetItemsUseCase
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.util.DomainException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val getCartSummaryUseCase: GetCartSummaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ItemListUiState>(ItemListUiState.Idle)
    val uiState: StateFlow<ItemListUiState> = _uiState.asStateFlow()

    val cartItems: StateFlow<List<CartItem>> = getCartItemsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val cartSummary: StateFlow<CartSummary> = cartItems
        .map { getCartSummaryUseCase(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CartSummary(0.0, 0.0, 0.0)
        )

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            _uiState.value = ItemListUiState.Loading
            getItemsUseCase().fold(
                onSuccess = { items ->
                    _uiState.value = ItemListUiState.Success(items)
                },
                onFailure = { throwable ->
                    val exception = throwable as? DomainException
                        ?: DomainException.UnknownException

                    _uiState.value = ItemListUiState.Error(
                        message = exception.message
                            ?: "Something went wrong. Please try again.",
                        isNetworkError = exception is DomainException.NoInternetException
                                      || exception is DomainException.RequestTimeoutException
                    )
                }
            )
        }
    }

    fun addItemToCart(item: Item) {
        viewModelScope.launch {
            addToCartUseCase(item)
        }
    }
}
