package com.ups.android.apps.feature.onboarding.presentation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ups.android.apps.feature.onboarding.presentation.mvi.Event
import com.ups.android.apps.feature.onboarding.presentation.mvi.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun WelcomeScreen(
    viewmodel: OnBoardingViewModel = koinViewModel<OnBoardingViewModel>(),
    onNextScreen: () -> Unit
) {
    Welcome(
        dispatch = viewmodel::event,
        onNextScreen = onNextScreen
    )
}

@Composable
fun Welcome(
    dispatch: (Event) -> Unit,
    onNextScreen: () -> Unit
) {
    Button(onClick = {
        dispatch(Event.OnBoardingProgress)
        onNextScreen()
    }) {
        Text(text = stringResource(id = R.string.welcome_getting_started))
    }
}