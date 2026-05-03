package com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase

import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.Item
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.repository.ShopRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    suspend operator fun invoke(item: Item): Result<Unit> {
        return repository.addToCart(item)
    }
}
