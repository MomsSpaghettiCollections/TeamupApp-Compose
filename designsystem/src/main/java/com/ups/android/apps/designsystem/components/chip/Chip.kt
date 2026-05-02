package com.ups.android.apps.designsystem.components.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.ups.android.apps.common.model.Category
import com.ups.android.apps.designsystem.R
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions


@Composable
fun SubCategoryChip(
    text: String,
    icon: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.surfaceContainerHigh else
        MaterialTheme.colorScheme.surface

    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = LocalDimensions.current.dimensions64))
            .border(
                width = LocalDimensions.current.dimensions2,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(size = LocalDimensions.current.dimensions64)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(size = LocalDimensions.current.dimensions64)
            )
            .padding(
                horizontal = LocalDimensions.current.dimensions12,
                vertical = LocalDimensions.current.dimensions8
            )
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(text = icon, color = Color.Blue)
            Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions4))
            TextCompose(text = text, textType = TextType.LABEL_SMALL)
        }

    )

}


@Composable
fun SubCategories(
    subcategories: List<Category>,
    onSelect: (Int) -> Unit
) {

    val scrollState = rememberScrollState()
    val selectedItemIndex = remember { mutableIntStateOf(-1) }

    Column(content = {
        TextCompose(
            text = stringResource(id = R.string.txt_subcategory),
            textType = TextType.LABEL_SMALL
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions4))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8),
            content = {
                subcategories.forEachIndexed { index, category ->
                    SubCategoryChip(
                        isSelected = selectedItemIndex.intValue == index,
                        text = category.name,
                        icon = category.icon,
                        onClick = {
                            selectedItemIndex.intValue = index
                            onSelect(category.id)
                        }
                    )
                }

            })
    })


}