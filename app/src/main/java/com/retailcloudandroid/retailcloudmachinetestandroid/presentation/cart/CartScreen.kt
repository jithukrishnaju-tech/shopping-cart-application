package com.retailcloudandroid.retailcloudmachinetestandroid.presentation.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.cart.components.CartItemRow
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.cart.components.CartSummaryCard
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.common.components.EmptyScreen
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.common.components.ErrorScreen
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.common.components.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onNavigateBack: () -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val state = uiState) {
                is CartUiState.Loading -> LoadingScreen()
                is CartUiState.Empty -> EmptyScreen()
                is CartUiState.Error -> ErrorScreen(
                    message = state.message,
                    isNetworkError = false,
                    onRetry = { /* Not applicable if no manual load */ }
                )
                is CartUiState.Success -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(state.items, key = { it.itemId }) { cartItem ->
                                CartItemRow(cartItem = cartItem)
                            }
                        }
                        CartSummaryCard(summary = state.summary)
                    }
                }
            }
        }
    }
}
