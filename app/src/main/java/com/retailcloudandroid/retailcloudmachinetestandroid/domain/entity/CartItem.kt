package com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity

data class CartItem(
    val itemId: String,
    val itemName: String,
    val sellingPrice: Double,
    val taxPercentage: Double,
    val quantity: Int
) {
    val subtotal: Double = sellingPrice * quantity
    val taxAmount: Double = subtotal * (taxPercentage / 100.0)
    val total: Double = subtotal + taxAmount
}
