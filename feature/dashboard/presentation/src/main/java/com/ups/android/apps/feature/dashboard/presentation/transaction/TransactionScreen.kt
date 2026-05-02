package com.ups.android.apps.feature.dashboard.presentation.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ups.android.apps.common.model.DateFormatPattern
import com.ups.android.apps.common.model.toDateFormat
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.components.transaction.TransactionItem
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.feature.dashboard.presentation.transaction.mvi.TransactionEvent
import com.ups.android.apps.feature.dashboard.presentation.transaction.mvi.TransactionViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel = koinViewModel<TransactionViewModel>()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.event(event = TransactionEvent.LoadTransaction)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {

            if (state.isLoading == false &&
                state.transaction.isNotEmpty()
            ) {

                LazyColumn(
                    modifier = Modifier.padding(all = 4.dp),
                    content = {

                        state.transaction.groupBy { it.date }.forEach { date, transactions ->

                            stickyHeader(content = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = MaterialTheme.colorScheme.surface
                                        )
                                        .padding(
                                            vertical = LocalDimensions.current.dimensions8
                                        ),
                                    content = {

                                        TextCompose(
                                            text = date.toDateFormat(DateFormatPattern.LONG),
                                            textType = TextType.LABEL_SMALL,
                                            modifier = Modifier.padding(start = 16.dp)
                                        )
                                    })
                            })

                            items(items = transactions, itemContent = {
                                TransactionItem(
                                    transaction = it,
                                    onTransactionItemClicked = {

                                    }
                                )
                            })
                        }
                    })
            }
        }
    )
}