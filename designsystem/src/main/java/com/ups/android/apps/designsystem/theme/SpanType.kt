package com.ups.android.apps.designsystem.theme

import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import kotlin.collections.forEach
import kotlin.text.indexOf
import kotlin.text.substring

sealed class SpanType(
    open val text: String
) {
    data class UrlSpan(
        override val text: String,
        val url: String,
        val styles: TextLinkStyles
    ) : SpanType(text)

    data class ClickSpan(
        override val text: String,
        val url: String,
        val styles: TextLinkStyles,
        val onClick: () -> Unit
    ) : SpanType(text)

    data class HeadingSpan(
        override val text: String,
        val styles: SpanStyle,
    ) : SpanType(text)
}

fun createAnnotatedString(
    fullText: String,
    spans: List<SpanType>
) = buildAnnotatedString {
    var currentIndex = 0
    spans.forEach { spanInfo ->
        val start = fullText.indexOf(spanInfo.text, currentIndex)
        if (start != -1) {
            append(fullText.substring(currentIndex, start))

            when (spanInfo) {
                is SpanType.UrlSpan -> with(spanInfo) {
                    withLink(
                        LinkAnnotation.Url(
                            url, styles
                        )
                    ) {
                        append(text)
                    }
                }

                is SpanType.ClickSpan -> with(spanInfo) {
                    withLink(
                        LinkAnnotation.Clickable(
                            text,
                            styles
                        ) {
                            onClick
                        }
                    ) {
                        append(text)
                    }
                }

                is SpanType.HeadingSpan -> with(spanInfo) {
                    withStyle(styles) {
                        append(text)
                    }
                }
            }
            currentIndex = start + spanInfo.text.length
        }
    }
    append(fullText.substring(currentIndex))
}