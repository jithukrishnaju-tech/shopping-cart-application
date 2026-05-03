package com.retailcloudandroid.retailcloudmachinetestandroid.data.mapper

import com.retailcloudandroid.retailcloudmachinetestandroid.data.remote.dto.ItemDto
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.Item

fun ItemDto.toDomain(): Item {
    return Item(
        id = itemID ?: "",
        name = itemName ?: "Unknown",
        sellingPrice = sellingPrice ?: 0.0,
        taxPercentage = taxPercentage ?: 0.0
    )
}
