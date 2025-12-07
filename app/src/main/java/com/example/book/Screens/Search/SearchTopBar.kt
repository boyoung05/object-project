package com.example.book.Screens.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SearchTopBar(
    query: String, // 현재 입력된 검색어
    onQueryChange: (String) -> Unit, // 글자가 바뀔 때마다 호출
    onSearch: (String) -> Unit // 검색 버튼/아이콘 눌렀을 때 호출
) {
    Column {
        Text(
            text = "책 제목/저자/ISBN 검색",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        color = Color(0xFF333333)
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearch(query)
                        }
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.CenterStart
                        ){
                            if(query.isEmpty()) {
                                Text(
                                    text = "제목 또는 저자 또는 ISBN 입력",
                                    color = Color(0xFFB0B0B0),
                                    fontSize = 13.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                IconButton(
                    onClick = {onSearch(query)}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "검색"
                    )
                }
            }
        }
    }
}