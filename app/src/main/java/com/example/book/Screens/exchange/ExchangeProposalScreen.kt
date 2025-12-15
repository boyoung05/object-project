package com.example.book.Screens.exchange

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.book.Screens.exchange.components.*
import com.example.book.model.Book
import com.example.book.model.Exchange
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.FieldValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeProposalScreen(
    navController: NavController,
    opponentUid: String        // 🔥 BookDetail에서 전달받아야 함
) {

    val myUid = FirebaseAuth.getInstance().currentUser!!.uid

    var myBooks by remember { mutableStateOf<List<Book>>(emptyList()) }
    var selectedBooks by remember { mutableStateOf<List<Book>>(emptyList()) }
    var showBookSheet by remember { mutableStateOf(false) }

    // 거래 방식
    var tradeMethod by remember { mutableStateOf("직거래") }

    // 직거래 입력
    var meetPlace by remember { mutableStateOf("") }
    var meetTime by remember { mutableStateOf("") }

    // 제안 내역
    var exchangeHistory by remember { mutableStateOf<List<Exchange>>(emptyList()) }

    LaunchedEffect(Unit) {
        loadMyBooks { myBooks = it }
        loadMyExchanges { exchangeHistory = it }
    }

    /* ===================== 책 선택 BottomSheet ===================== */
    if (showBookSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBookSheet = false },
            containerColor = Color.White
        ) {
            Text(
                "내 책 선택 (최대 3권)",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )

            myBooks.forEach { book ->
                val selected = selectedBooks.any { it.id == book.id }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedBooks =
                                if (selected) {
                                    selectedBooks.filterNot { it.id == book.id }
                                } else {
                                    if (selectedBooks.size < 3) selectedBooks + book
                                    else selectedBooks
                                }
                        }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(book.title)
                    if (selected) {
                        Text("선택됨", color = Color(0xFFB9D86B))
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }

    /* ===================== 메인 UI ===================== */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Text("교환 제안", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(20.dp))

        Text("내 책 선택 (최대 3권)", fontWeight = FontWeight.SemiBold)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(3) { index ->
                BookCard(
                    book = selectedBooks.getOrNull(index),
                    selected = index < selectedBooks.size,
                    onClick = { showBookSheet = true }
                )
            }
        }

        Spacer(Modifier.height(8.dp))
        Text(
            "선택됨: ${
                selectedBooks.joinToString { it.title }
                    .ifEmpty { "없음 (카드를 눌러 선택하세요)" }
            }",
            fontSize = 12.sp,
            color = Color.Gray
        )

        /* ===================== 거래 방식 ===================== */

        Spacer(Modifier.height(28.dp))
        Text("거래 방식 선택", fontWeight = FontWeight.SemiBold)

        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TradeToggleItem(
                text = "직거래",
                selected = tradeMethod == "직거래",
                onClick = { tradeMethod = "직거래" }
            )
            TradeToggleItem(
                text = "택배",
                selected = tradeMethod == "택배",
                onClick = { tradeMethod = "택배" }
            )
        }

        if (tradeMethod == "직거래") {

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = meetPlace,
                onValueChange = { meetPlace = it },
                placeholder = { Text("예: 항공대 중앙도서관 앞") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = meetTime,
                onValueChange = { meetTime = it },
                placeholder = { Text("예: 5월 10일 (토) 15:00") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
        }

        /* ===================== 제안 보내기 ===================== */

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                createExchangeAndChatRoom(
                    myUid = myUid,
                    opponentUid = opponentUid,
                    selectedBooks = selectedBooks,
                    tradeMethod = tradeMethod,
                    meetPlace = meetPlace,
                    meetTime = meetTime
                ) { chatRoomId ->
                    navController.navigate("chat_room/$chatRoomId")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB9D86B),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("제안 보내기", fontWeight = FontWeight.SemiBold)
        }

        /* ===================== 제안 내역 ===================== */

        Spacer(Modifier.height(32.dp))
        Text("제안 내역", fontWeight = FontWeight.SemiBold)

        Spacer(Modifier.height(10.dp))

        exchangeHistory.forEach { exchange ->
            ProposalHistoryItem(
                date = exchange.createdAt?.toDate()?.toString() ?: "",
                status = exchange.status,
                detail = "내 책 ${exchange.proposerBooks.size}권 · ${exchange.tradeMethod}"
            )
        }
    }
}

/* ===================== Firestore 로직 ===================== */

fun createExchangeAndChatRoom(
    myUid: String,
    opponentUid: String,
    selectedBooks: List<Book>,
    tradeMethod: String,
    meetPlace: String,
    meetTime: String,
    onSuccess: (chatRoomId: String) -> Unit
) {
    val db = FirebaseFirestore.getInstance()

    val exchangeData = hashMapOf(
        "proposerId" to myUid,
        "opponentId" to opponentUid,
        "proposerBooks" to selectedBooks.map { it.id },
        "tradeMethod" to tradeMethod,
        "status" to "대기중",
        "createdAt" to FieldValue.serverTimestamp()
    )

    if (tradeMethod == "직거래") {
        exchangeData["meetPlace"] = meetPlace
        exchangeData["meetTime"] = meetTime
    }

    db.collection("exchanges")
        .add(exchangeData)
        .addOnSuccessListener { exchangeDoc ->

            val chatRoomData = hashMapOf(
                "exchangeId" to exchangeDoc.id,
                "participants" to listOf(myUid, opponentUid),
                "createdAt" to FieldValue.serverTimestamp(),
                "lastMessage" to ""
            )

            db.collection("chatRooms")
                .add(chatRoomData)
                .addOnSuccessListener { chatRoomDoc ->
                    onSuccess(chatRoomDoc.id)
                }
        }
}

fun loadMyBooks(
    onResult: (List<Book>) -> Unit
) {
    val uid = FirebaseAuth.getInstance().currentUser!!.uid

    FirebaseFirestore.getInstance()
        .collection("books")
        .whereEqualTo("ownerId", uid)
        .get()
        .addOnSuccessListener { snapshot ->
            val books = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Book::class.java)?.copy(id = doc.id)
            }
            onResult(books)
        }
}

fun loadMyExchanges(
    onResult: (List<Exchange>) -> Unit
) {
    val uid = FirebaseAuth.getInstance().currentUser!!.uid

    FirebaseFirestore.getInstance()
        .collection("exchanges")
        .whereEqualTo("proposerId", uid)
        .orderBy("createdAt", Query.Direction.DESCENDING)
        .get()
        .addOnSuccessListener { snapshot ->
            val list = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Exchange::class.java)?.copy(id = doc.id)
            }
            onResult(list)
        }
}
