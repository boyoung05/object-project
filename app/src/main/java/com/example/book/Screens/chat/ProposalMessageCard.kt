package com.example.book.Screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProposalMessageCard(
    proposalData: Map<String, Any>,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFF7D8), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {

        Text("📦 교환 제안", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(8.dp))

        Text("내 책 수: ${(proposalData["bookCount"] ?: 0)}권")
        Text("거래 방식: ${proposalData["tradeMethod"]}")

        proposalData["meetPlace"]?.let {
            Text("장소: $it")
        }
        proposalData["meetTime"]?.let {
            Text("시간: $it")
        }

        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = onAccept,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB9D86B))
            ) {
                Text("수락")
            }
            OutlinedButton(onClick = onReject) {
                Text("거절")
            }
        }
    }
}
