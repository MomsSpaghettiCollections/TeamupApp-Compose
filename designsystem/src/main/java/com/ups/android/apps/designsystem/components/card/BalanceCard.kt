package com.ups.android.apps.designsystem.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.ups.android.apps.designsystem.R
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions

@Composable
fun BalanceCard(
    priceBalance: Double,
    onDetailClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Column(
                modifier = Modifier
                    .padding(all = LocalDimensions.current.dimensions16),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(space = LocalDimensions.current.dimensions4)
            ) {
                TextCompose(
                    text = stringResource(id = R.string.txt_cancel),
                    textType = TextType.HEADLINE_MEDIUM
                )
                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions2))
                TextCompose(
                    text = priceBalance.toString(),
                    textType = TextType.TITLE_LARGE
                )
            }
            Spacer(modifier = Modifier.weight(weight = 1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .padding(end = LocalDimensions.current.dimensions16)
                    .clickable(
                        onClick = {
                            onDetailClick()
                        },
                    )
                    .size(
                        size = LocalDimensions.current.dimensions32
                    )
            )
        }
    )
}

