package com.ups.android.apps.designsystem.components.textfiled

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.designsystem.theme.graphExpense
import com.ups.android.apps.designsystem.theme.graphIncome
import com.ups.android.apps.designsystem.theme.gray




@Composable
fun AmountEditText(
    defaultCurrency: String,
    defaultValue: String,
    isIncome: Boolean = true,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        value = defaultValue,
        onValueChange = { newValue ->
            if (newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        textStyle = TextStyle(
            color = if (isIncome) graphIncome else graphExpense,
            fontSize = 48.sp,
            textAlign = TextAlign.Start
        ),
        decorationBox = { innerTextField ->
            if (defaultValue.isEmpty()) {
                TextCompose(
                    text = defaultCurrency + "0",
                    textType = if (isIncome) TextType.DISPLAY_MEDIUM_INCOME else
                        TextType.DISPLAY_MEDIUM_EXPENSE
                )
            } else {
                Text(
                    text = defaultCurrency + defaultValue,
                    style = TextStyle(
                        color = if (isIncome) graphIncome else graphExpense,
                        fontSize = 48.sp
                    )
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TransactionEditText(
    defaultValue: String,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        value = defaultValue,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onSurface ,
            fontSize = 48.sp,
            textAlign = TextAlign.Start
        ),
        decorationBox = { innerTextField ->
            if (defaultValue.isEmpty()) {
                Text(
                    text = "Enter Note",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurfaceVariant ,
                        fontSize = 48.sp,
                        textAlign = TextAlign.Start
                    )
                )
            } else innerTextField()
        },
        modifier = Modifier.fillMaxWidth()
    )
}