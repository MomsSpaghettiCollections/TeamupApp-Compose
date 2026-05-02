package com.ups.android.apps.feature.transaction.presentation.selectcategory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ups.android.apps.common.model.Categorization
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.feature.transaction.presentation.selectcategory.mvi.CategoryEvent
import com.ups.android.apps.feature.transaction.presentation.selectcategory.mvi.CategoryUiState
import com.ups.android.apps.feature.transaction.presentation.selectcategory.mvi.CategoryViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SelectCategoryScreen(
    viewModel: CategoryViewModel = koinViewModel<CategoryViewModel>(),
    isIncome: Boolean,
    onCategorySelected: (Categorization) -> Unit,
    onBackPressed: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.event(CategoryEvent.Initialize(isIncome = isIncome))
    }

    val state = viewModel.state.collectAsStateWithLifecycle()

    when (val uiState = state.value) {
        is CategoryUiState.CategoriesLoaded -> SelectCategory(
            categories = uiState.categories,
            onCategorySelected = onCategorySelected,
            onBackPressed = onBackPressed,
        )

        CategoryUiState.Initial -> LinearProgressIndicator()
    }

}

@Composable
fun SelectCategory(
    categories: List<Categorization>,
    onCategorySelected: (Categorization) -> Unit,
    onBackPressed: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        content = {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {

                    TextCompose(
                        text = stringResource(id = com.ups.android.apps.feature.transaction.presentation.R.string.updated),
                        textType = TextType.TITLE_MEDIUM
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable(
                            onClick = {
                                onBackPressed()
                            }
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions12))

            LazyColumn(content = {
                items(items = categories, itemContent = {
                    CategoryItemRow(
                        categorization = it,
                        onCategorySelected = {
                            onCategorySelected(it)
                        }
                    )
                })
            })
        }
    )

}

@Composable
fun CategoryItemRow(
    categorization: Categorization,
    onCategorySelected: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                onCategorySelected()
            })
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            Row(verticalAlignment = Alignment.CenterVertically, content = {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .border(
                            width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainerHigh,
                            shape = CircleShape
                        )
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainerHigh,
                            shape = CircleShape
                        )
                        .padding(all = 8.dp),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(text = categorization.category.icon)
                    })

                Spacer(modifier = Modifier.size(16.dp))
                Text(text = categorization.category.name)
            })

        }
    )
}