package com.ups.android.apps.feature.dashboard.presentation.analytics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ups.android.apps.common.model.feature.MonthlyData
import com.ups.android.apps.designsystem.components.card.IncomeExpenseCards
import com.ups.android.apps.designsystem.components.card.MonthlyBarCard
import com.ups.android.apps.feature.dashboard.presentation.analytics.mvi.AnalyticsEvent
import com.ups.android.apps.feature.dashboard.presentation.analytics.mvi.AnalyticsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AnalyticsScreen(
    viewModel: AnalyticsViewModel = koinViewModel<AnalyticsViewModel>(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = Unit) {
        viewModel.event(AnalyticsEvent.LoadData)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                all = 16.dp,
            )
            .verticalScroll(
                state = scrollState
            ),
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        ),
        content = {

            IncomeExpenseCards(
                income = state.analyticsData.incomeData.graphData,
                expense = state.analyticsData.expenseData.graphData
            )


            MonthlyBarCard(
                data = state.analyticsData.monthlyData.map {
                    MonthlyData(
                        label = it.label,
                        income = it.income,
                        expense = it.expense
                    )
                }.reversed(),
                onClick = {

                }
            )
        })

}


