package com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase

import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartItem
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    operator fun invoke(): Flow<List<CartItem>> {
        return repository.getCartItems()
    }
}
