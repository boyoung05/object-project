package com.example.book.Screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.book.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

@Composable
fun ChatRoomScreen(chatRoomId: String) {

    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }

    LaunchedEffect(chatRoomId) {
        FirebaseFirestore.getInstance()
            .collection("messages")
            .document(chatRoomId)
            .collection("items")
            .orderBy("createdAt", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    messages = snapshot.documents.mapNotNull {
                        it.toObject(Message::class.java)?.copy(id = it.id)
                    }
                }
            }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(messages) { msg ->
            when (msg.type) {
                "proposal" -> {
                    ProposalMessageCard(
                        proposalData = msg.proposalData ?: emptyMap(),
                        onAccept = {
                            // 🔥 여기서 exchange status → 수락
                        },
                        onReject = {
                            // 🔥 여기서 exchange status → 거절
                        }
                    )
                }
                else -> {
                    Text(msg.text)
                }
            }
        }
    }
}
