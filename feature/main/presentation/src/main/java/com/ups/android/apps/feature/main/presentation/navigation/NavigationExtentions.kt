package com.ups.android.apps.feature.main.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

inline fun <reified T> NavGraphBuilder.teamupComposable(
    noinline content: @Composable (NavBackStackEntry) -> Unit
) where T : Any, T : Destinations {
    composable<T>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(1000)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(1000)
            )
        },
        content = { backStackEntry ->
            content(backStackEntry)
        }
    )
}