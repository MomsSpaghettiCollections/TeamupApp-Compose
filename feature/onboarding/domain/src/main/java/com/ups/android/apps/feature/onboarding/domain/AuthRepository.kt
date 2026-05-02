package com.ups.android.apps.feature.onboarding.domain

import arrow.core.Either
import com.ups.android.apps.common.model.AuthConfig
import com.ups.android.apps.common.model.SignInError
import com.ups.android.apps.common.model.User

interface AuthRepository {
    val currentsUser: User?
    suspend fun signInAnonymous(): Either<SignInError, AuthConfig>
    suspend fun signIn(token: String): Either<SignInError, AuthConfig>
}