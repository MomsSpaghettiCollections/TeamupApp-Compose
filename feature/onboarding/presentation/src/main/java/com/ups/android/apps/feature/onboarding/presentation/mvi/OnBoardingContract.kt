package com.ups.android.apps.feature.onboarding.presentation.mvi

import androidx.credentials.Credential
import com.ups.android.apps.designsystem.UiText

sealed interface Event {
    data object OnBoardingProgress : Event
    data object OnBoardingCompleted : Event
    data object SignInAnonymous : Event
    data class SignInWithGoogle(val credential: Credential) : Event
}

sealed interface Effects {
    data object NavigateToHome : Effects
    data class ShowError(val message: UiText): Effects
}