package com.example.book.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.book.ui.screens.splash.SplashScreen
import com.example.book.ui.screens.main.MainScreen
import com.example.book.ui.screens.auth.LoginScreen
import com.example.book.ui.screens.auth.RegisterScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }

        composable("login") { LoginScreen(navController) }

        composable("register") { RegisterScreen(navController) }

        //  MainScreen에 rootNavController 전달
        composable("main") {
            MainScreen(navController)
        }
    }
}
