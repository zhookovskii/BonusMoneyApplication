package com.example.bonusmoney.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import com.example.bonusmoney.api.Company

@Composable
fun Navigation(companies: LazyPagingItems<Company>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("loading") {
            LoadingScreen(companies)
        }
    }
}