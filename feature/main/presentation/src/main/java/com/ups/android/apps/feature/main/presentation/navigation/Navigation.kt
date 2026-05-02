package com.ups.android.apps.feature.main.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ups.android.apps.common.model.OnBoardingConfig
import com.ups.android.apps.designsystem.UiText
import com.ups.android.apps.designsystem.components.navigation.BottomNavBar
import com.ups.android.apps.feature.dashboard.presentation.analytics.AnalyticsScreen
import com.ups.android.apps.feature.dashboard.presentation.home.HomeScreen
import com.ups.android.apps.feature.dashboard.presentation.settings.SettingScreen
import com.ups.android.apps.feature.dashboard.presentation.transaction.TransactionScreen
import com.ups.android.apps.feature.onboarding.presentation.IntroductionScreen
import com.ups.android.apps.feature.onboarding.presentation.SignInScreen
import com.ups.android.apps.feature.onboarding.presentation.WelcomeScreen


fun NavGraphBuilder.navigateToOnBoarding(
    navController: NavHostController,
    onBoardingConfig: OnBoardingConfig,
    onError: (Boolean, UiText) -> Unit
) {
    navigation<Graphs.Onboarding>(
        startDestination = getDestination(onBoardingConfig)
    ) {
        teamupComposable<Destinations.Welcome> {
            WelcomeScreen(
                onNextScreen = {
                    navController.navigate(route = Destinations.Introduction)
                }
            )
        }
        teamupComposable<Destinations.Introduction> {
            IntroductionScreen(
                onNextScreen = {
                    navController.navigate(route = Destinations.SignIn)
                }
            )
        }
        teamupComposable<Destinations.SignIn> {
            SignInScreen(
                onError = onError,
                onNextScreen = {
                    navController.navigate(route = Destinations.Home)
                }
            )
        }
    }
}

private fun getDestination(onBoardingConfig: OnBoardingConfig) = when (
    onBoardingConfig
) {
    OnBoardingConfig.NOT_STARTED -> Destinations.Welcome
    OnBoardingConfig.IN_PROGRESS -> Destinations.Introduction
    OnBoardingConfig.COMPLETED -> Destinations.SignIn
}


fun NavGraphBuilder.navigateToDashBoard(
    navController: NavHostController
) {
    navigation<Graphs.Dashboard>(
        startDestination = Destinations.Home
    ) {
        teamupComposable<Destinations.Home> {
            val nav = rememberNavController()
            AuthenticatedHomeScreen(
                navController = nav,
                function = {
                    navController.navigate(
                        route = Destinations.AddTransaction
                    )
                }
            )
        }
    }
}

@Composable
fun AuthenticatedHomeScreen(
    navController: NavHostController,
    function: () -> Unit
) {

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedIndex = selectedTab,
                onItemSelected = {
                    if (selectedTab != it) {
                        selectedTab = it
                        when (selectedTab) {
                            0 -> navController.navigate(route = Destinations.Dash)
                            1 -> navController.navigate(route = Destinations.Transactions)
                            2 -> navController.navigate(route = Destinations.Analytics)
                            3 -> navController.navigate(route = Destinations.Settings)
                            4 -> function()
                        }
                    }
                }
            )
        },
        content = { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Destinations.Dash,
                modifier = Modifier.padding(
                    paddingValues = innerPadding
                ),
                builder = {
                    teamupComposable<Destinations.Dash> {
                        HomeScreen(
                            onAddActionClicked = {

                            }
                        )

                    }
                    teamupComposable<Destinations.Settings> {
                        SettingScreen(
                            onSelect = {

                            }
                        )
                    }
                    teamupComposable<Destinations.Analytics> {
                        AnalyticsScreen()
                    }
                    teamupComposable<Destinations.Transactions> {
                        TransactionScreen()
                    }

                }
            )
        }
    )

}