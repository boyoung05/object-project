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
    val users: List<String> = emptyList()
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
                        doc.toObject(ChatRoom::class.java)
                            ?.copy(id = doc.id)
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

        Text("채팅", fontSize = 22.sp, color = Color.Black)
        Spacer(Modifier.height(20.dp))

        if (chatRooms.isEmpty()) {
            Text(
                "아직 채팅방이 없습니다",
                fontSize = 14.sp,
                color = Color.Gray
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(chatRooms) { room ->
                    ChatRoomItem(
                        chatRoom = room,
                        myUid = myUid,
                        onClick = {
                            navController.navigate("chat_room/${room.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChatRoomItem(
    chatRoom: ChatRoom,
    myUid: String,
    onClick: () -> Unit
) {
    val opponentUid = chatRoom.users.firstOrNull { it != myUid } ?: "알 수 없음"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "상대 UID: $opponentUid",
                fontSize = 15.sp,
                color = Color.Black
            )
            Text(
                text = "채팅방 ID: ${chatRoom.id.take(6)}...",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}
