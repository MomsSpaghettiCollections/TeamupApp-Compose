package com.ups.android.apps.designsystem.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign


@Composable
fun TextCompose(
    text: String,
    textType: TextType,
    textAlign: TextAlign = TextAlign.Left,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = textAlign,
        style = textType.getStyle(),
        color = textType.getColor(),
        modifier = modifier
    )
}

@Composable
fun TextCompose(
    text: AnnotatedString,
    textType: TextType,
    textAlign: TextAlign = TextAlign.Left,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = textAlign,
        style = textType.getStyle(),
        color = textType.getColor(),
        modifier = modifier
    )
}