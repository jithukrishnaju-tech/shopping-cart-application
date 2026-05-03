package com.retailcloudandroid.retailcloudmachinetestandroid.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val itemId: String,
    val itemName: String,
    val sellingPrice: Double,
    val taxPercentage: Double,
    val quantity: Int
)
