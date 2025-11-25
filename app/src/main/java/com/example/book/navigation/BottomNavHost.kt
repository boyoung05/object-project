package com.example.book.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.book.Screens.BookInfo.BookInfoScreen
import com.example.book.Screens.Search.SearchScreen

import com.example.book.Screens.home.HomeScreen        // ⭐ 수정: HomeScreen은 book 패키지


import com.example.book.Screens.exchange.ExchangeProposalScreen

@Composable
fun BottomNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        // 1. 홈 화면
        composable("home") { HomeScreen(navController) }

        // 2. 검색 화면
        composable("search") { SearchScreen(navController) }
        // 5. 책 상세 화면
        composable("bookinfo") { BookInfoScreen(navController) }

        // 6. 교환 제안 화면
        composable("exchange_proposal") { ExchangeProposalScreen(navController) }

   }
}
