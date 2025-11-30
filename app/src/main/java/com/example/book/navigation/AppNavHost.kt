package com.example.book.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.book.ui.screens.splash.SplashScreen
import com.example.book.ui.screens.main.MainScreen
import com.example.book.Screens.mypage.TradeListScreen


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }

        // 🔥 MainScreen은 navController를 받지 않음
        composable("main") {
            MainScreen()
        }
        composable("exchange_proposal") {
        }

        composable("trade_list") {
            TradeListScreen()    // ← TradeListScreen.kt 를 호출
        }


    }
}
