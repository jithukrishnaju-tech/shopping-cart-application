package com.retailcloudandroid.retailcloudmachinetestandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.retailcloudandroid.retailcloudmachinetestandroid.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_items")
    fun getCartItems(): Flow<List<CartItemEntity>>

    @Query("SELECT * FROM cart_items WHERE itemId = :itemId")
    suspend fun getCartItemById(itemId: String): CartItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartItemEntity)

    @Transaction
    suspend fun insertOrIncrementQuantity(item: CartItemEntity) {
        val existingItem = getCartItemById(item.itemId)
        if (existingItem != null) {
            insert(existingItem.copy(quantity = existingItem.quantity + 1))
        } else {
            insert(item.copy(quantity = 1))
        }
    }

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}
