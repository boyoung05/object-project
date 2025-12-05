package com.example.book.model

import com.google.firebase.events.Publisher

data class Book(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val publisher: String = "",
    val condition: String = "", // 도서 상태: 상, 중, 하
    val tradeMethod: String = "", // 거래 방식: 직거래 / 택배
    val category: String = "", // 도서 종류: 에세이 / 소설 / 교재
    // 도서 종류는 더 많을 수 있으나 구현하기 힘드니 일단 즐겨 보는 종류로만 구성했습니다
    val imageUrl: String = "", // 지금은 로컬 Uri 문자열 저장
    // Storage로 하려면 유료..
    val ownerId: String = "" // 해당 책을 등록한 사용자 uid
)
