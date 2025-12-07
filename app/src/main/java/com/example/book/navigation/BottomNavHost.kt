package com.example.book.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.book.Screens.BookInfo.BookInfoScreen
import com.example.book.Screens.Search.SearchScreen
import com.example.book.Screens.home.HomeScreen
import com.example.book.Screens.mypage.MyPageScreen
import com.example.book.Screens.exchange.ExchangeProposalScreen
import com.example.book.Screens.mypage.UploadBookScreen
import com.example.book.Screens.chat.ChatRoomScreen

@Composable
fun BottomNavHost(
    navController: NavHostController,
    rootNavController: NavHostController,    //  추가
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        composable("home") { HomeScreen(navController) }

        composable("search") { SearchScreen(navController) }

        composable("chat") { ChatRoomScreen() }

        //  수정함 (원래: MyPageScreen에는 rootNavController 전달)
        composable("mypage") { MyPageScreen(navController) }

        // 책 등록 화면
        composable ("uploadBook") {UploadBookScreen(navController)}

        // 책 상세
        composable(
            route = "bookinfo/{bookId}",
            arguments = listOf(
                navArgument("bookId") { type = NavType.StringType}
            )
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            BookInfoScreen(navController, bookId)
        }

        composable("exchange_proposal") { ExchangeProposalScreen(navController) }
    }
}
