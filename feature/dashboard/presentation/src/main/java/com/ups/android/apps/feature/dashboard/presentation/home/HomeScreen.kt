package com.ups.android.apps.feature.dashboard.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ups.android.apps.designsystem.components.card.BalanceCard
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.components.transaction.TransactionItem
import com.ups.android.apps.feature.dashboard.presentation.home.mvi.HomeEvent
import com.ups.android.apps.feature.dashboard.presentation.home.mvi.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    onAddActionClicked: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = Unit) {
        viewModel.event(HomeEvent.LoadData)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(
            all = 16.dp,
        ).verticalScroll(
            state = scrollState
        ),
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        ),
        content = {

            BalanceCard(
                priceBalance = state.homeData.priceBalance.currentBalance,
                onDetailClick = {

                }
            )


            if (state.homeData.transactionList.isNotEmpty()){
                state.homeData.transactionList.forEach {
                    TransactionItem(
                        transaction = it,
                        onTransactionItemClicked = {

                        }
                    )
                }
            }else{

                Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
                TextCompose(
                    text = "No Transaction Found",
                    textType = TextType.LABEL_SMALL
                )

            }
        }
    )
}