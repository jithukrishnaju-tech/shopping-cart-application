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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
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
                onFailure = { exception ->
                    val isNetworkError = exception is UnknownHostException || exception is SocketTimeoutException
                    val message = when (exception) {
                        is UnknownHostException -> "No internet connection"
                        is SocketTimeoutException -> "Request timed out"
                        else -> "Something went wrong, please try again"
                    }
                    _uiState.value = ItemListUiState.Error(message, isNetworkError)
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
