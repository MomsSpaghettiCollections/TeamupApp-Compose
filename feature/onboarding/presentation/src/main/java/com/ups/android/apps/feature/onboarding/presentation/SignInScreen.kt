package com.ups.android.apps.feature.onboarding.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ups.android.apps.designsystem.UiText
import com.ups.android.apps.designsystem.components.button.GoogleButton
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalDimensions
import com.ups.android.apps.designsystem.theme.SpanType
import com.ups.android.apps.designsystem.theme.createAnnotatedString
import com.ups.android.apps.feature.onboarding.presentation.mvi.Effects
import com.ups.android.apps.feature.onboarding.presentation.mvi.Event
import com.ups.android.apps.feature.onboarding.presentation.mvi.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SignInScreen(
    viewmodel: OnBoardingViewModel = koinViewModel<OnBoardingViewModel>(),
    onError: (Boolean, UiText) -> Unit,
    onNextScreen: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewmodel.effects.collect {
            when (it) {
                Effects.NavigateToHome -> onNextScreen()
                is Effects.ShowError -> onError(true, it.message)
            }
        }
    }
    SignIn(
        onNextScreen = onNextScreen,
        onError = onError,
        dispatch = viewmodel::event
    )
}

@Composable
fun SignIn(
    onNextScreen: () -> Unit,
    onError: (Boolean, UiText) -> Unit,
    dispatch: (Event) -> Unit
) {
    Column {
        TextCompose(
            text = createAnnotatedString(
                fullText = stringResource(
                    id = R.string.sign_in_title
                ),
                spans = listOf(
                    SpanType.HeadingSpan(
                        text = stringResource(id = R.string.sign_in_title_span),
                        styles = SpanStyle(color = MaterialTheme.colorScheme.primary)
                    )
                )
            ), textType = TextType.HEADLINE_LARGE,
            modifier = Modifier.padding(
                top = LocalDimensions.current.dimensions48,
                start = LocalDimensions.current.dimensions16
            )
        )

        Spacer(modifier = Modifier.weight(1f))


        GoogleButton(
            text = "SignIn With Google",
            onFailure = {
                onError(true, it)
            },
            onSuccess = { credential ->
            //   dispatch(Event.SignInWithGoogle(credential = credential))
                onNextScreen()
            }
        )

        Button(onClick = {
          //  dispatch(Event.SignInAnonymous)
            onNextScreen()
        }) { Text("Continue as guest") }

        TextCompose(
            textType = TextType.BODY_LARGE,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(all = LocalDimensions.current.dimensions8),
            text = createAnnotatedString(
                fullText = stringResource(R.string.sign_in_tnc_span),
                spans = listOf(
                    SpanType.UrlSpan(
                        text = stringResource(R.string.sign_in_tnc_span1),
                        url = "https://www.google.com",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    ),
                    SpanType.UrlSpan(
                        text = stringResource(R.string.sign_in_tnc_span2),
                        url = "https://www.facebook.com",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    )
                )
            )
        )

    }
}