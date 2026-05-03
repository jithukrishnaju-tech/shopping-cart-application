package com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase

import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.Item
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.repository.ShopRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    suspend operator fun invoke(): Result<List<Item>> {
        return repository.getItems()
    }
}
