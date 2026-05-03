package com.retailcloudandroid.retailcloudmachinetestandroid.presentation.cart.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.entity.CartSummary
import com.retailcloudandroid.retailcloudmachinetestandroid.presentation.common.utils.toPrice

@Composable
fun CartSummaryCard(
    summary: CartSummary,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SummaryRow(label = "Subtotal", value = summary.subtotal.toPrice())
            SummaryRow(label = "Tax", value = summary.totalTax.toPrice())
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            SummaryRow(
                label = "Total",
                value = summary.grandTotal.toPrice(),
                isBold = true,
                fontSize = 20
            )
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String,
    isBold: Boolean = false,
    fontSize: Int = 16
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                fontSize = fontSize.sp
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                fontSize = fontSize.sp
            ),
            color = if (isBold) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}
