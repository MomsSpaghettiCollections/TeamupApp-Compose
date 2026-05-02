package com.ups.android.apps.designsystem.components.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ups.android.apps.common.model.feature.Transaction
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.designsystem.theme.LocalShapes


@Composable
fun TransactionItem(
    transaction: Transaction,
    onTransactionItemClicked: () -> Unit,
) {

    TransactionInfo(transaction = transaction)
}

@Composable
fun TransactionInfo(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = LocalDimensions.current.dimensions8),
        verticalAlignment = Alignment.CenterVertically,
        content = {

            TransactionCategoryIcon(
                icon = "Aa"
            )

            Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions16))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(space = LocalDimensions.current.dimensions2),
                content = {

                    TextCompose(
                        text = transaction.toString(),
                        textType = TextType.LABEL_SMALL
                    )
                }
            )
        }
    )
}


@Composable
fun TransactionCategoryIcon(
    icon: String
) {
    Box(
        modifier = Modifier
            .size(
                size = LocalDimensions.current.dimensions48
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = LocalShapes.current.dots
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = LocalShapes.current.dots
            )
            .padding(
                all = LocalDimensions.current.dimensions8
            ),
        contentAlignment = Alignment.Center,
        content = {
            TextCompose(
                text = icon,
                textType = TextType.LABEL_SMALL
            )
        }
    )
}