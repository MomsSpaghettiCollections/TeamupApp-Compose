package com.ups.android.apps.designsystem.components.chart


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ups.android.apps.common.model.feature.MonthlyData
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.designsystem.theme.graphExpense
import com.ups.android.apps.designsystem.theme.graphIncome
import com.ups.android.apps.designsystem.theme.gray
import kotlin.collections.forEach
import kotlin.collections.forEachIndexed
import kotlin.collections.maxOfOrNull
import kotlin.ranges.coerceAtLeast
import kotlin.ranges.reversed
import kotlin.ranges.step


@Composable
fun BarItem(
    monthData: MonthlyData,
    isSelected: Boolean,
    maxValue: Int, onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = LocalDimensions.current.dimensions8)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isSelected) gray.copy(alpha = 0.5f) else
                        Color.Transparent
                )
                .padding(LocalDimensions.current.dimensions4)
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(
                            ((monthData.income * 200.0 / maxValue).coerceAtLeast(1.0).toInt()).dp
                        )
                        .background(graphIncome, shape = RoundedCornerShape(4.dp))
                )
                Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions4))
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height((monthData.expense * 200 / maxValue).dp)
                        .background(graphExpense, shape = RoundedCornerShape(4.dp))
                )
            }
        }
        Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions4))
        TextCompose(text = monthData.label, textType = TextType.LABEL_SMALL)

    }
}

@Composable
fun BarChart(
    selectedIndex: Int,
    listData: List<MonthlyData>,
    onClick: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    val maxValue = (listData.maxOfOrNull { kotlin.comparisons.maxOf(it.income, it.expense) } ?: 1) + 10

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = LocalDimensions.current.dimensions8),
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            modifier = Modifier.padding(end = LocalDimensions.current.dimensions8)
        ) {
            (0..maxValue step maxValue / 4).reversed().forEach {
                TextCompose(
                    text = "$it",
                    textType = TextType.LABEL_SMALL,
                    modifier = Modifier.padding(bottom = LocalDimensions.current.dimensions24)
                )
            }
        }
        Row(
            modifier = Modifier
                .horizontalScroll(
                    state = scrollState
                )
                .fillMaxWidth(), verticalAlignment = Alignment.Bottom
        ) {
            listData.forEachIndexed { index, monthData ->
                BarItem(
                    monthData = monthData,
                    isSelected = index == selectedIndex,
                    maxValue = maxValue,
                    onClick = {
                        onClick(index)
                    }
                )
            }
        }
    }
}