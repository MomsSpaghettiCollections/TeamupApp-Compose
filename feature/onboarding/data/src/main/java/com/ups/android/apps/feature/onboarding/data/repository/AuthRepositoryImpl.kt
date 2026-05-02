package com.ups.android.apps.feature.onboarding.data.repository

import arrow.core.Either
import com.ups.android.apps.common.model.AuthConfig
import com.ups.android.apps.common.model.SignInError
import com.ups.android.apps.common.model.User
import com.ups.android.apps.feature.onboarding.domain.AuthRepository
import com.ups.android.apps.network.firebase_auth.FirebaseAuthHelper

class AuthRepositoryImpl(
    private val helper: FirebaseAuthHelper
) : AuthRepository {
    override val currentsUser: User?
        get() = helper.getCurrentUser()

    override suspend fun signInAnonymous(): Either<SignInError, AuthConfig> =
        helper.signInAnonymous()

    override suspend fun signIn(token: String): Either<SignInError, AuthConfig> =
        helper.signInWithGoogle(token)
}