package com.retailcloudandroid.retailcloudmachinetestandroid.domain.usecase

import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartItem
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartSummary
import javax.inject.Inject
import kotlin.math.roundToInt

class GetCartSummaryUseCase @Inject constructor() {
    operator fun invoke(cartItems: List<CartItem>): CartSummary {
        val subtotal = cartItems.sumOf { it.subtotal }
        val totalTax = cartItems.sumOf { it.taxAmount }
        val grandTotal = subtotal + totalTax

        return CartSummary(
            subtotal = subtotal.roundToTwoDecimals(),
            totalTax = totalTax.roundToTwoDecimals(),
            grandTotal = grandTotal.roundToTwoDecimals()
        )
    }

    private fun Double.roundToTwoDecimals(): Double {
        return (this * 100.0).roundToInt() / 100.0
    }
}
