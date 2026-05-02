package com.ups.android.apps.common.model

interface SignInError {
    data object InvalidCredentials : SignInError
    data object InvalidUser : SignInError
    data object UnknownError : SignInError
}