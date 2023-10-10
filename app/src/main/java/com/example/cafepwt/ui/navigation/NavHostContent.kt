package com.example.cafepwt.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cafepwt.ui.screen.HomeScreen
import com.example.cafepwt.ui.screen.ProfileScreen
import com.example.cafepwt.ui.screen.ScreenDetail

@Composable
fun NavHostContent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navigateToDetail = { cafeId ->
                navigateToCafeDetail(navController, cafeId)
            })
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navigateBack = { navController.navigateUp() })
        }
        composable(
            route = Screen.CafeDetail.route,
            arguments = listOf(navArgument("cafeId") { type = NavType.LongType })
        ) {
            val id = it.arguments?.getLong("cafeId") ?: -1L
            ScreenDetail(cafeId = id)
        }
    }
}

private fun navigateToCafeDetail(navController: NavHostController, cafeId: Long) {
    navController.navigate(Screen.CafeDetail.createRoute(cafeId))
}
