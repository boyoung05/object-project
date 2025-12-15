package com.example.book.Screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

data class ChatRoom(
    val id: String = "",
    val users: List<String> = emptyList(),
    val opponentName: String = "",
    val opponentSchool: String = "",
    val proposalBookTitles: List<String> = emptyList(),
    val lastMessage: String = ""
)

@Composable
fun ChatScreen(navController: NavController) {

    var chatRooms by remember { mutableStateOf<List<ChatRoom>>(emptyList()) }
    val myUid = FirebaseAuth.getInstance().currentUser?.uid ?: return

    LaunchedEffect(Unit) {
        FirebaseFirestore.getInstance()
            .collection("chats")
            .whereArrayContains("users", myUid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    chatRooms = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(ChatRoom::class.java)?.copy(id = doc.id)
                    }
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .padding(20.dp)
    ) {

        Text("채팅", fontSize = 22.sp)
        Spacer(Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(chatRooms) { room ->
                ChatRoomItem(
                    room = room,
                    onClick = {
                        navController.navigate("chat_room/${room.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun ChatRoomItem(
    room: ChatRoom,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {

            Text(
                text = "${room.opponentName} (${room.opponentSchool})",
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = if (room.proposalBookTitles.isNotEmpty())
                    "📘 ${room.proposalBookTitles.joinToString(" · ")}"
                else
                    room.lastMessage,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}
