package com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity

data class Item(
    val id: String,
    val name: String,
    val sellingPrice: Double,
    val taxPercentage: Double
)
