package com.ups.android.apps.network.firebase_auth.di


import com.google.firebase.auth.FirebaseAuth
import com.ups.android.apps.network.firebase_auth.FirebaseAuthHelper
import org.koin.dsl.module


val firebaseAuthModule = module {
    single { FirebaseAuth.getInstance() }

    single { FirebaseAuthHelper(auth = get()) }
}