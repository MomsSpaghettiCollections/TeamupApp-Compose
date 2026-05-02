package com.ups.android.apps.feature.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ups.android.apps.common.model.AuthConfig
import com.ups.android.apps.common.model.Categorization
import com.ups.android.apps.designsystem.UiText
import com.ups.android.apps.designsystem.components.ScreenContainer
import com.ups.android.apps.designsystem.theme.TeamupAppComposeTheme
import com.ups.android.apps.feature.main.presentation.mvi.UiState
import com.ups.android.apps.feature.main.presentation.navigation.Destinations
import com.ups.android.apps.feature.main.presentation.navigation.Graphs
import com.ups.android.apps.feature.main.presentation.navigation.navigateToDashBoard
import com.ups.android.apps.feature.main.presentation.navigation.navigateToOnBoarding
import com.ups.android.apps.feature.main.presentation.navigation.teamupComposable
import com.ups.android.apps.feature.transaction.presentation.addtransaction.AddTransactionScreen
import com.ups.android.apps.feature.transaction.presentation.selectcategory.SelectCategoryScreen
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

private const val CATEGORIZATION_KEY = "CATEGORIZATION"

@Composable
fun MainScreen(
    uiState: UiState.Success
) {
    val navController = rememberNavController()

    TeamupAppComposeTheme(content = {
        var showErrorMessage by remember { mutableStateOf(value = false) }
        var errorMessage by remember { mutableStateOf<UiText>(value = UiText.EmptyString) }
        var isErrorMessage by remember { mutableStateOf(value = true) }

        LaunchedEffect(key1 = showErrorMessage) {
            if (showErrorMessage) {
                delay(timeMillis = 3000)
                showErrorMessage = false
            }
        }


        ScreenContainer(
            showBanner = showErrorMessage,
            bannerMessage = errorMessage,
            isErrorBanner = isErrorMessage,
            content = {
                NavHost(
                    navController = navController,
                    startDestination = getDestination(authConfig = uiState.data.authStatus)
                ) {
                    navigateToOnBoarding(
                        navController = navController,
                        onBoardingConfig = uiState.data.onBoardingStatus,
                        onError = { showError, message ->
                            showErrorMessage = showError
                            errorMessage = message
                        }
                    )
                    navigateToDashBoard(
                        navController = navController
                    )

                    teamupComposable<Destinations.AddTransaction> {
                        var categorization by remember { mutableStateOf<Categorization?>(value = null) }

                        val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

                        val result1Json = savedStateHandle?.getStateFlow<String?>(
                            key = CATEGORIZATION_KEY,
                            initialValue = null
                        )

                        LaunchedEffect(key1 = result1Json) {
                            result1Json?.value?.let { json ->
                                categorization = Json.decodeFromString<Categorization>(json)
                                savedStateHandle.remove<String>(CATEGORIZATION_KEY)
                            }
                        }
                        AddTransactionScreen(
                            categorization = categorization,
                            onCategoryChange = {
                                categorization = it
                            },
                            onSelectCategory = {
                                navController.navigate(
                                    route = Destinations.SelectCategory(isIncome = it)
                                )
                            },
                            showBanner = {  showBanner, message, isError ->
                                showErrorMessage = showBanner
                                errorMessage = message
                                isErrorMessage = isError
                            }
                        )
                    }
                    teamupComposable<Destinations.SelectCategory> { backStackEntry ->
                        val isIncome = backStackEntry.arguments?.getBoolean("isIncome") ?: false

                        SelectCategoryScreen(
                            isIncome = isIncome,
                            onCategorySelected = { category ->
                                val json = Json.encodeToString<Categorization>(value = category)
                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                    key = CATEGORIZATION_KEY,
                                    json
                                )
                                navController.popBackStack()
                            },
                            onBackPressed = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }

        )
    })
}


private fun getDestination(authConfig: AuthConfig) = if (authConfig == AuthConfig.AUTHENTICATED)
    Graphs.Dashboard else Graphs.Onboarding