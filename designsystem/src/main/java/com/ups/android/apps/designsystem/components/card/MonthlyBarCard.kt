package com.ups.android.apps.designsystem.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ups.android.apps.common.model.feature.MonthlyData
import com.ups.android.apps.designsystem.components.chart.BarChart
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.designsystem.theme.graphExpense
import com.ups.android.apps.designsystem.theme.graphIncome


@Composable
fun MonthlyBarCard(
    data: List<MonthlyData>,
    onClick: (String) -> Unit
) {
    var selectedMonthIndex by remember(data) {
        mutableIntStateOf(data.size - 1)
    }
    Column(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = LocalDimensions.current.dimensions16
                ),
            horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8)
        ) {
            TextCompose(
                text = "Monthly",
                textType = TextType.HEADLINE_MEDIUM
            )
            Spacer(modifier = Modifier.weight(1f))
            LegendItem("Income", graphIncome)
            LegendItem("Expense", graphExpense)
        }
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions4))
        BarChart(
            selectedIndex = selectedMonthIndex,
            listData = data,
            onClick = {
                selectedMonthIndex = it

                onClick(data[it].label)
            }
        )
    }
}

@Composable
fun LegendItem(
    label: String,
    color: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(LocalDimensions.current.dimensions12)
                .background(color, shape = RoundedCornerShape(LocalDimensions.current.dimensions8))
        )
        Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions4))
        TextCompose(text = label, textType = TextType.LABEL_SMALL)
    }

}