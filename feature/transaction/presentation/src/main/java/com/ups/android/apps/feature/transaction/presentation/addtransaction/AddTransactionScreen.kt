package com.ups.android.apps.feature.transaction.presentation.addtransaction

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ups.android.apps.common.model.Categorization
import com.ups.android.apps.common.model.feature.ModelTransactionType
import com.ups.android.apps.common.model.feature.Transaction
import com.ups.android.apps.designsystem.UiText
import com.ups.android.apps.designsystem.components.button.ButtonCompose
import com.ups.android.apps.designsystem.components.chip.SubCategories
import com.ups.android.apps.designsystem.components.tab.AnimatedTab
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.components.textfiled.AmountEditText
import com.ups.android.apps.designsystem.components.textfiled.SelectCategory
import com.ups.android.apps.designsystem.components.textfiled.TransactionEditText
import com.ups.android.apps.feature.transaction.presentation.R
import com.ups.android.apps.feature.transaction.presentation.addtransaction.mvi.AddTransactionEffect
import com.ups.android.apps.feature.transaction.presentation.addtransaction.mvi.AddTransactionEvent
import com.ups.android.apps.feature.transaction.presentation.addtransaction.mvi.AddTransactionUiState
import com.ups.android.apps.feature.transaction.presentation.addtransaction.mvi.AddTransactionViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel = koinViewModel<AddTransactionViewModel>(),
    categorization: Categorization?,
    onSelectCategory: (Boolean) -> Unit,
    onCategoryChange: (Categorization) -> Unit,
    showBanner: (Boolean, UiText, Boolean) -> Unit
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.addTransactionEffect.collect {
            when (it) {
                is AddTransactionEffect.CategoriesUpdated -> onCategoryChange(it.categories)
                is AddTransactionEffect.SuccessSuccessBanner -> showBanner(true, it.text, false)
            }
        }
    }

    when (val state = state.value) {
        is AddTransactionUiState.CategoriesLoaded -> AddTransaction(
            categorization = categorization ?: state.categories,
            onSelectCategory = onSelectCategory,
            dispatch = viewModel::event
        )

        AddTransactionUiState.Initial -> LinearProgressIndicator()
    }
}

@Composable
fun AddTransaction(
    categorization: Categorization,
    onSelectCategory: (Boolean) -> Unit,
    dispatch: (AddTransactionEvent) -> Unit,
) {
    val scrollState = rememberScrollState()

    var isIncome by rememberSaveable { mutableStateOf(true) }
    var amount by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .imePadding()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(24.dp),
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

                            }
                        )
                    )
                }
            )

            AnimatedTab(
                tabList = stringArrayResource(id = R.array.txt_transaction_Types),
                onClick = { index ->
                    isIncome = index == 0
                    dispatch(AddTransactionEvent.UpdateCategories(isIncome = isIncome))
                }
            )

            AmountEditText(
                defaultValue = amount,
                isIncome = isIncome,
                onValueChange = { newValue ->
                    amount = newValue
                },
                defaultCurrency = "$"
            )


            SelectCategory(
                categoryType = categorization.category.name,
                onSelectCategory = {
                    onSelectCategory(isIncome)
                }
            )

            SubCategories(
                subcategories = categorization.subcategories,
                onSelect = { subcategoryId ->

                }
            )

            TransactionEditText(
                defaultValue = notes,
                onValueChange = { newValue ->
                    notes = newValue
                },
            )

            ButtonCompose(
                text = "ADD ",
                isFullWidth = true,
                onClick = {
                    dispatch(
                        AddTransactionEvent.SaveTransaction(
                            transaction = Transaction(
                                amount = 100.0,
                                notes = "Aa",
                                type = ModelTransactionType.CREDIT,
                                date = 0,
                                categoryId = 0,
                                subcategoryId = 1
                            )
                        )
                    )
                }
            )
        }
    )
}