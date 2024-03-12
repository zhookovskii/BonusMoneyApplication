package com.example.bonusmoney.ui

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Loading : Screen("loading")
}