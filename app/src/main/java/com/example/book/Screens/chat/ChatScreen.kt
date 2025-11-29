package com.example.book.Screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .padding(20.dp)
    ) {

        Text("채팅", fontSize = 22.sp, color = Color.Black)
        Spacer(Modifier.height(20.dp))

        // 예시 더미
        ChatListItem("스토너 교환 대화", onClick = {
            navController.navigate("chat_room/1")
        })

        Spacer(Modifier.height(12.dp))

        ChatListItem("자료구조 교환 대화", onClick = {
            navController.navigate("chat_room/2")
        })
    }
}

@Composable
fun ChatListItem(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(title, fontSize = 16.sp, color = Color.Black)
    }
}
