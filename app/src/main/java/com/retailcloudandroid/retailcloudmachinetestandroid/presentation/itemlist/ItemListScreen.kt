package com.retailcloudandroid.retailcloudmachinetestandroid.presentation.itemlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.common.components.ErrorScreen
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.common.components.LoadingScreen
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.common.utils.toPrice
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.itemlist.components.ItemRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen(
    onNavigateToCart: () -> Unit,
    viewModel: ItemListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val cartItems by viewModel.cartItems.collectAsStateWithLifecycle()
    val cartSummary by viewModel.cartSummary.collectAsStateWithLifecycle()

    val totalQuantity = cartItems.sumOf { it.quantity }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Items") },
                actions = {
                    Button(
                        onClick = onNavigateToCart,
                        enabled = cartItems.isNotEmpty(),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("View Cart ($totalQuantity)")
                    }
                }
            )
        },
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                Surface(
                    tonalElevation = 8.dp,
                    shadowElevation = 8.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Sub: ${cartSummary.subtotal.toPrice()} | Tax: ${cartSummary.totalTax.toPrice()} | Total: ${cartSummary.grandTotal.toPrice()}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val state = uiState) {
                is ItemListUiState.Loading -> LoadingScreen()
                is ItemListUiState.Error -> ErrorScreen(
                    message = state.message,
                    isNetworkError = state.isNetworkError,
                    onRetry = { viewModel.loadItems() }
                )
                is ItemListUiState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.items, key = { it.id }) { item ->
                            ItemRow(
                                item = item,
                                cartItems = cartItems,
                                onAddToCart = { viewModel.addItemToCart(it) }
                            )
                        }
                    }
                }
                else -> Unit
            }
        }
    }
}
