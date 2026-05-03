package com.retailcloudandroid.retailcloudmachinetestandroid.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("itemID")
    val itemID: String?,
    @SerializedName("itemName")
    val itemName: String?,
    @SerializedName("sellingPrice")
    val sellingPrice: Double?,
    @SerializedName("taxPercentage")
    val taxPercentage: Double?
)
