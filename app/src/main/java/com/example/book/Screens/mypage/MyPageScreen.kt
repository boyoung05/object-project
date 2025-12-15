package com.example.book.Screens.mypage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@Composable
fun MyPageScreen(rootNavController: NavHostController) {

    // ---------------- Firebase ----------------
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val user = auth.currentUser
    val uid = user?.uid

    // ---------------- 상태 ----------------
    var nickname by remember { mutableStateOf("로딩중...") }
    var school by remember { mutableStateOf("") }
    var isEmailVerified by remember { mutableStateOf(user?.isEmailVerified == true) }

    // Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // ---------------- Firestore 데이터 로드 ----------------
    LaunchedEffect(uid) {
        if (uid != null) {
            db.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { doc ->
                    nickname = doc.getString("nickname") ?: "이름 없음"
                    school = doc.getString("school") ?: ""
                }
        }
    }

    // ---------------- UI ----------------
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF6F7FB))
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // =========================== 프로필 카드 ===========================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "profile",
                        tint = Color(0xFF666666),
                        modifier = Modifier
                            .size(56.dp)
                            .background(Color(0xFFF2F2F2), CircleShape)
                            .padding(12.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = nickname,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        if (school.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = school,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .background(
                                    if (isEmailVerified) Color(0xFFDFF5E1)
                                    else Color(0xFFFFF3B8),
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = if (isEmailVerified) "이메일 인증 완료" else "미인증",
                                fontSize = 12.sp,
                                color = Color(0xFF333333)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // =========================== 인증 ===========================
            Text(text = "인증", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // 🔥 이메일 인증 카드
                IconCard(
                    label = if (isEmailVerified) "이메일 인증됨" else "이메일 인증",
                    icon = Icons.Default.Email
                ) {

                    if (isEmailVerified) {
                        scope.launch {
                            snackbarHostState.showSnackbar("이미 이메일 인증이 완료되었습니다")
                        }
                        return@IconCard
                    }

                    user?.sendEmailVerification()
                        ?.addOnSuccessListener {
                            scope.launch {
                                snackbarHostState.showSnackbar("인증 메일을 보냈습니다. 이메일을 확인해주세요")
                            }
                        }
                        ?.addOnFailureListener {
                            scope.launch {
                                snackbarHostState.showSnackbar("인증 메일 전송 실패")
                            }
                        }
                }

                // 학생증 (아직 미구현)
                IconCard(
                    label = "학생증",
                    icon = Icons.Default.School
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🔄 인증 상태 새로고침 버튼
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    user?.reload()
                        ?.addOnSuccessListener {
                            isEmailVerified =
                                FirebaseAuth.getInstance().currentUser?.isEmailVerified == true

                            scope.launch {
                                snackbarHostState.showSnackbar("인증 상태를 새로고침했습니다")
                            }
                        }
                }
            ) {
                Text("인증 상태 새로고침")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // =========================== 내 책 관리 ===========================
            Text(text = "내 책 관리", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(16.dp))

            ManageItem("책 등록", Icons.Default.Book) {
                rootNavController.navigate("uploadBook")
            }

            Spacer(modifier = Modifier.height(12.dp))

            ManageItem("거래 완료", Icons.Default.Check) {
                // TODO 거래 완료 화면
            }

            Spacer(modifier = Modifier.height(32.dp))

            // =========================== 설정 + 로그아웃 ===========================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    onClick = {},
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("알림 설정")
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    onClick = {
                        FirebaseAuth.getInstance().signOut()
                        rootNavController.navigate("login") {
                            popUpTo("main") { inclusive = true }
                            popUpTo("splash") { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFF7D8)
                    )
                ) {
                    Text("로그아웃", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

// =============================================================
// 공용 UI 컴포넌트
// =============================================================

@Composable
fun IconCard(
    label: String,
    icon: ImageVector,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            }
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(40.dp),
            tint = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, fontSize = 15.sp)
    }
}

@Composable
fun ManageItem(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(32.dp),
            tint = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, fontSize = 16.sp)
    }
}
