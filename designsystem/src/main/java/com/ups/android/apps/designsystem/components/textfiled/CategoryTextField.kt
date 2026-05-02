package com.ups.android.apps.designsystem.components.textfiled

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ups.android.apps.designsystem.R
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions


@Composable
fun SelectCategory(
    categoryType: String,
    onSelectCategory: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectCategory() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {

            Column(content = {
                TextCompose(
                    text = stringResource(id = R.string.txt_category),
                    textType = TextType.LABEL_SMALL
                )
                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions4))
                TextCompose(
                    text = categoryType,
                    textType = TextType.TITLE_MEDIUM
                )
            })

            Icon(
                modifier = Modifier
                    .size(LocalDimensions.current.dimensions32)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        shape = CircleShape
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = CircleShape
                    )
                    .padding(all = LocalDimensions.current.dimensions8),
                imageVector = Icons.Default.AddCard,
                contentDescription = categoryType,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    )

}

