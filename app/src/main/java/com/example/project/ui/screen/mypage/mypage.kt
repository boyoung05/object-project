package com.example.project.ui.screen.mypage

import androidx.compose.foundation.background
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

// -------------------------------------------------------------
//  🔥 마이페이지 메인 화면
// -------------------------------------------------------------
@Composable
fun MyPageScreen() {

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

                // 프로필 아이콘
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
                            .background(
                                Color(0xFFFFF3B8),
                                RoundedCornerShape(12.dp)
                            )
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

        ManageItem("책 등록", Icons.Default.Book)
        Spacer(modifier = Modifier.height(12.dp))
        ManageItem("거래 완료", Icons.Default.Check)

        Spacer(modifier = Modifier.height(32.dp))

        // --------------------------- 교환 + 통계 ---------------------------
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconCard("교환 횟수", Icons.Default.Star)
            IconCard("장르 통계", Icons.Default.BarChart)
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

// -------------------------------------------------------------
//  🔥 인증 카드 / 통계 카드 공용 UI
// -------------------------------------------------------------
@Composable
fun IconCard(label: String, icon: ImageVector) {

    Column(
        modifier = Modifier
            .width(150.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
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

// -------------------------------------------------------------
//  🔥 책 등록 / 거래 완료 공용 UI
// -------------------------------------------------------------
@Composable
fun ManageItem(label: String, icon: ImageVector) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
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
