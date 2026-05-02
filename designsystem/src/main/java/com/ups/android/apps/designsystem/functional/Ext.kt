package com.ups.android.apps.designsystem.functional

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun <T> Flow<T>.stateInWhileActive(
    scope: CoroutineScope,
    initial: T,
    preFetch: () -> Unit
): StateFlow<T> {
    return onStart {
        preFetch()
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = initial
    )
}