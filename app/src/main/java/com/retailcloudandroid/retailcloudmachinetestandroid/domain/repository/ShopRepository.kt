package com.retailcloudandroid.retailcloudmachinetestandroid.domain.repository

import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartItem
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.Item
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
    suspend fun getItems(): Result<List<Item>>
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addToCart(item: Item)
    suspend fun clearCart()
}
