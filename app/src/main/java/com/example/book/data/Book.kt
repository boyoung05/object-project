package com.example.book.data

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val thumbnailResId: Int,
    val condition: String, // 상, 중, 하
    val method: String, // 직거래, 택배,
    val publisher: String // 출판사
)