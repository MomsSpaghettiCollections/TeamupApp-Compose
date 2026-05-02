package com.ups.android.apps.designsystem.components.chart

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.collections.first
import kotlin.collections.isNotEmpty
import kotlin.collections.mapIndexed
import kotlin.collections.maxOrNull
import kotlin.ranges.until
import kotlin.to

@Composable
fun LineChart(
    expenses: List<Float>,
    color: Color
) {
    Canvas(
        modifier = Modifier.size(100.dp, 50.dp)
    ) {
        val path = Path()
        if (expenses.isNotEmpty()) {
            val maxAmount = expenses.maxOrNull() ?: 1f
            val stepX = size.width / (expenses.size - 1)
            val scaleY = size.height / maxAmount

            val points = expenses.mapIndexed { index, value ->
                index * stepX to size.height - (value * scaleY)
            }

            path.moveTo(points.first().first, points.first().second)

            for (i in 1 until points.size - 1) {
                val (x1, y1) = points[i]
                val (x2, y2) = points[i + 1]

                val midX2 = (x1 + x2) / 2
                val midY2 = (y1 + y2) / 2
                path.quadraticTo(x1, y1, midX2, midY2)
            }
        }
        drawPath(path = path, color = color, style = Stroke(width = 3.dp.toPx()))
    }
}