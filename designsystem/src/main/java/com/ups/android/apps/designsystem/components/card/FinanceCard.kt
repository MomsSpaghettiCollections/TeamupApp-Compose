package com.ups.android.apps.designsystem.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ups.android.apps.designsystem.R
import com.ups.android.apps.designsystem.components.chart.LineChart
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.components.util.amountColor
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.designsystem.theme.LocalWindowType
import com.ups.android.apps.designsystem.theme.WindowType

@Composable
fun FinanceCard(
    expense: List<Float>,
    isIncome: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(
                all = LocalDimensions.current.dimensions16,
            )
        ) {
            TextCompose(
                text = if (isIncome) stringResource(id = R.string.txt_cancel)
                else stringResource(id = R.string.txt_cancel),
                textType = TextType.HEADLINE_MEDIUM
            )

            TextCompose(
                text = stringResource(id = R.string.txt_cancel),
                textType = TextType.LABEL_SMALL
            )
            TextCompose(
                text = expense.sum().toDouble().toString(),
                textType = if (isIncome) TextType.DISPLAY_MEDIUM_INCOME
                else TextType.DISPLAY_MEDIUM_EXPENSE
            )
        }

        LineChart(
            expenses = expense,
            color = isIncome.amountColor()
        )
    }
}

@Composable
fun IncomeExpenseCards(
    income: List<Float>,
    expense: List<Float>
) {
    when (LocalWindowType.current) {
        is WindowType.Compact -> {
            FinanceCard(
                expense = income,
                isIncome = true
            )
            FinanceCard(
                expense = expense,
                isIncome = false
            )
        }

        else -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8)
            ) {
                FinanceCard(
                    expense = income,
                    isIncome = true
                )
                FinanceCard(
                    expense = expense,
                    isIncome = false
                )

            }
        }
    }
}

