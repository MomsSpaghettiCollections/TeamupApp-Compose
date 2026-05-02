package com.ups.android.apps.feature.onboarding.presentation.mvi

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.ups.android.apps.common.model.OnBoardingConfig
import com.ups.android.apps.common.model.SignInError
import com.ups.android.apps.designsystem.UiText
import com.ups.android.apps.feature.onboarding.domain.AuthRepository
import com.ups.android.apps.feature.onboarding.domain.PreferenceRepository
import com.ups.android.apps.feature.onboarding.presentation.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val preferenceRepository: PreferenceRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val mutableSharedFlow: MutableSharedFlow<Effects> = MutableSharedFlow()
    val effects: SharedFlow<Effects>
        get() = mutableSharedFlow.asSharedFlow()


    fun event(event: Event) {
        when (event) {
            Event.OnBoardingCompleted -> viewModelScope.launch {
                preferenceRepository.saveUserOnboardingStatus(onBoardingConfig = OnBoardingConfig.COMPLETED)
            }

            Event.OnBoardingProgress -> viewModelScope.launch {
                preferenceRepository.saveUserOnboardingStatus(onBoardingConfig = OnBoardingConfig.IN_PROGRESS)
            }

            Event.SignInAnonymous -> viewModelScope.launch {
                authRepository.signInAnonymous().fold(ifLeft = { signInError ->
                    mutableSharedFlow.emit(
                        value = Effects.ShowError(getErrorMessage(signInError))
                    )
                }, ifRight = { authConfig ->
                    preferenceRepository.saveUserAuth(authConfig)
                    mutableSharedFlow.emit(value = Effects.NavigateToHome)
                })
            }

            is Event.SignInWithGoogle -> viewModelScope.launch {

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
                    return@launch

                if (event.credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {

                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(event.credential.data)

                    authRepository.signIn(
                        token = googleIdTokenCredential.idToken
                    ).fold(ifLeft = { signError ->
                        mutableSharedFlow.emit(
                            value = Effects.ShowError(
                                message = getErrorMessage(signError)
                            )
                        )
                    }, ifRight = { authConfig ->
                        preferenceRepository.saveUserAuth(authConfig)
                        mutableSharedFlow.emit(value = Effects.NavigateToHome)
                    })

                }
            }
        }
    }


    private fun getErrorMessage(signError: SignInError) =
        when (signError) {
            SignInError.UnknownError -> UiText.StringResource(R.string.error_unknown)
            SignInError.InvalidUser -> UiText.StringResource(R.string.error_invalid_user)
            SignInError.InvalidCredentials -> UiText.StringResource(R.string.error_invalid_credential)
            else -> UiText.StringResource(R.string.error_unknown)
        }
}