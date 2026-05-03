package com.retailcloudandroid.retailcloudmachinetestandroid.data.remote.api

import com.retailcloudandroid.retailcloudmachinetestandroid.data.remote.dto.ItemDto
import retrofit2.http.GET

interface ShopApi {
    @GET("/")
    suspend fun getItems(): List<ItemDto>
}
