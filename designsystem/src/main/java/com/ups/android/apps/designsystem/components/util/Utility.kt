package com.ups.android.apps.designsystem.components.util

import androidx.compose.runtime.Composable
import com.ups.android.apps.common.model.feature.ModelTransactionType
import com.ups.android.apps.designsystem.theme.graphExpense
import com.ups.android.apps.designsystem.theme.graphIncome

@Composable
fun ModelTransactionType.getTextColor() =
    when (this) {
        ModelTransactionType.DEBIT -> graphExpense
        ModelTransactionType.CREDIT -> graphIncome
    }

@Composable
fun Boolean.amountColor() = if (this) {
    graphIncome
} else graphExpense