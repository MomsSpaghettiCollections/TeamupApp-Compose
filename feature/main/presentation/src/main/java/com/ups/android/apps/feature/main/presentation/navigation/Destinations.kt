package com.ups.android.apps.feature.main.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Graphs {
    @Serializable
    data object Onboarding : Graphs

    @Serializable
    data object Dashboard : Graphs
}

sealed interface Destinations {
    @Serializable
    data object SignIn : Destinations

    @Serializable
    data object Introduction : Destinations

    @Serializable
    data object Welcome : Destinations

    @Serializable
    data object Home : Destinations

    @Serializable
    data object Dash : Destinations

    @Serializable
    data object AddTransaction : Destinations

    @Serializable
    data class SelectCategory(val isIncome: Boolean): Destinations

    @Serializable
    data object Settings : Destinations

    @Serializable
    data object Analytics : Destinations

    @Serializable
    data object Transactions : Destinations

}