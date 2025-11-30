package com.example.book.Screens.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun MyPageScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7FB))
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // --------------------------- 프로필 카드 ---------------------------
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
                        text = "John Doe",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFF3B8), RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "미인증",
                            fontSize = 12.sp,
                            color = Color(0xFF6D6D6D)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        // --------------------------- 인증 ---------------------------
        Text(text = "인증", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconCard("이메일", Icons.Default.Email)
            IconCard("학생증", Icons.Default.School)
        }

        Spacer(modifier = Modifier.height(32.dp))


        // --------------------------- 내 책 관리 ---------------------------
        Text(text = "내 책 관리", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // 1) 책 등록
        ManageItem(
            label = "책 등록",
            icon = Icons.Default.Book,
            onClick = { navController.navigate("upload_book") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 2) 거래 완료 리스트
        ManageItem(
            label = "거래 완료",
            icon = Icons.Default.Check,
            onClick = { navController.navigate("trade_list") }
        )

        Spacer(modifier = Modifier.height(32.dp))


        // --------------------------- 교환 + 통계 ---------------------------
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 3) 교환 횟수 화면 이동
            IconCard(
                label = "교환 횟수",
                icon = Icons.Default.Star,
                onClick = { navController.navigate("exchange_count") }
            )

            // 4) 장르 통계 (지금은 기능 없음)
            IconCard(
                label = "장르 통계",
                icon = Icons.Default.BarChart,
                onClick = { /* 추후 구현 */ }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))


        // --------------------------- 나의 선호 장르 ---------------------------
        Text(text = "나의 선호 장르", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.White, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "그래프 이미지 자리", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(32.dp))


        // --------------------------- 알림 설정 + 로그아웃 ---------------------------
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
                onClick = {},
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


// ---------------------------------------------------------
// IconCard — 클릭 기능 추가 버전 ⭐
// ---------------------------------------------------------
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
            .padding(vertical = 20.dp)
            .let { if (onClick != null) it.clickable { onClick() } else it },
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


// ---------------------------------------------------------
// ManageItem — 클릭 가능 최종 버전 ⭐
// ---------------------------------------------------------
@Composable
fun ManageItem(
    label: String,
    icon: ImageVector,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
            .let { if (onClick != null) it.clickable { onClick() } else it },
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
