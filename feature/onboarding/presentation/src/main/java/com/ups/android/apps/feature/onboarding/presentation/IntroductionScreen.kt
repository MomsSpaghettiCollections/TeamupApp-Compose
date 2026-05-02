package com.ups.android.apps.feature.onboarding.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import com.ups.android.apps.designsystem.components.PagerWithIndicator
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.designsystem.theme.SpanType
import com.ups.android.apps.designsystem.theme.createAnnotatedString
import com.ups.android.apps.feature.onboarding.presentation.mvi.Event
import com.ups.android.apps.feature.onboarding.presentation.mvi.OnBoardingViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun IntroductionScreen(
    viewmodel: OnBoardingViewModel = koinViewModel<OnBoardingViewModel>(),
    onNextScreen: () -> Unit
) {
    Introduction(
        onNextScreen = onNextScreen,
        dispatch = viewmodel::event
    )
}

@Composable
fun Introduction(
    onNextScreen: () -> Unit,
    dispatch: (Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = LocalDimensions.current.dimensions16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()
        val pageCount = stringArrayResource(R.array.onboarding_title).size
        val pageState = rememberPagerState(
            initialPage = 0, initialPageOffsetFraction = 0f, pageCount = { pageCount })

        val buttonText = if (pageState.currentPage == pageCount - 1) {
            stringResource(R.string.welcome_getting_started)
        } else {
            stringResource(R.string.next)
        }

        PagerWithIndicator(
            modifier = Modifier
                .weight(1f)
                .padding(all = LocalDimensions.current.dimensions16),
            pageCount = pageCount,
            pageState = pageState,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = LocalDimensions.current.dimensions16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextCompose(
                        text = createAnnotatedString(
                            fullText = stringArrayResource(R.array.onboarding_title)[it],
                            spans = listOf(
                                SpanType.HeadingSpan(
                                    text = stringArrayResource(R.array.onboarding_title_spans)[it],
                                    styles = SpanStyle(color = MaterialTheme.colorScheme.primary)
                                )
                            )
                        ), textType = TextType.HEADLINE_MEDIUM
                    )
                    Spacer(modifier = Modifier.padding(all = LocalDimensions.current.dimensions8))
                }
            }
        )

        Button(onClick = {
            coroutineScope.launch {
                if (pageState.currentPage < pageCount - 1) {
                    pageState.animateScrollToPage(pageState.currentPage + 1)
                } else {
                    dispatch(Event.OnBoardingCompleted)
                    onNextScreen.invoke()
                }
            }

        }) {
            Text(buttonText)
        }
        Button(onClick = {
            dispatch(Event.OnBoardingCompleted)
            onNextScreen.invoke()
        }) {
            Text(
                text = stringResource(id = R.string.skip)
            )
        }
    }
}