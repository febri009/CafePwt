package com.example.cafepwt.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object CafeDetail: Screen("home/{cafeId}"){
        fun createRoute(cafeId: Long) = "home/$cafeId"
    }
}