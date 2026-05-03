package com.retailcloudandroid.retailcloudmachinetestandroid.data.mapper

import com.retailcloudandroid.retailcloudmachinetestandroid.data.local.entity.CartItemEntity
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartItem

fun CartItemEntity.toDomain(): CartItem {
    return CartItem(
        itemId = itemId,
        itemName = itemName,
        sellingPrice = sellingPrice,
        taxPercentage = taxPercentage,
        quantity = quantity
    )
}

fun CartItem.toEntity(): CartItemEntity {
    return CartItemEntity(
        itemId = itemId,
        itemName = itemName,
        sellingPrice = sellingPrice,
        taxPercentage = taxPercentage,
        quantity = quantity
    )
}
