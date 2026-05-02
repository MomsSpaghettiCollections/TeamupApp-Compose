package com.ups.android.apps.network.firebase_auth

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.ups.android.apps.common.model.AuthConfig
import com.ups.android.apps.common.model.SignInError
import com.ups.android.apps.common.model.User
import kotlinx.coroutines.tasks.await
import java.io.IOException


class FirebaseAuthHelper(
    private val auth: FirebaseAuth
) {
    fun getCurrentUser(): User? = auth.currentUser?.let {
        User(
            displayName = it.displayName.orEmpty(),
            displayProfileUrl = it.photoUrl.toString()
        )
    }

    suspend fun signInAnonymous(): Either<SignInError, AuthConfig> {
        return runCatching {
            auth.signInAnonymously().await()
            AuthConfig.GUEST.right()
        }.getOrElse { e ->
            SignInError.UnknownError.left()
        }
    }

    suspend fun signInWithGoogle(token: String): Either<SignInError, AuthConfig> {
        val authCredential = GoogleAuthProvider.getCredential(token, null)
        return runCatching {
            auth.signInWithCredential(authCredential).await()
            AuthConfig.AUTHENTICATED.right()
        }.getOrElse { e ->
            when (e) {
                is IOException -> SignInError.InvalidCredentials.left()
                is Exception -> SignInError.InvalidUser.left()
                else -> SignInError.UnknownError.left()
            }
        }
    }
}