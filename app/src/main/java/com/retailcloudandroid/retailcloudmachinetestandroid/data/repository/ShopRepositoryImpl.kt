package com.retailcloudandroid.retailcloudmachinetestandroid.data.repository

import com.retailcloudandroid.retailcloudmachinetestandroid.data.local.dao.CartDao
import com.retailcloudandroid.retailcloudmachinetestandroid.data.local.entity.CartItemEntity
import com.retailcloudandroid.retailcloudmachinetestandroid.data.mapper.toDomain
import com.retailcloudandroid.retailcloudmachinetestandroid.data.remote.api.ShopApi
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartItem
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.Item
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val shopApi: ShopApi,
    private val cartDao: CartDao
) : ShopRepository {

    override suspend fun getItems(): Result<List<Item>> {
        return runCatching {
            shopApi.getItems().map { it.toDomain() }
        }
    }

    override fun getCartItems(): Flow<List<CartItem>> {
        return cartDao.getCartItems().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToCart(item: Item) {
        cartDao.insertOrIncrementQuantity(
            CartItemEntity(
                itemId = item.id,
                itemName = item.name,
                sellingPrice = item.sellingPrice,
                taxPercentage = item.taxPercentage,
                quantity = 1
            )
        )
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }
}
