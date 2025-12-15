package com.example.book.model

import com.google.firebase.Timestamp

data class Exchange(
    val id: String = "",
    val proposerId: String = "",
    val proposerBooks: List<String> = emptyList(),
    val tradeMethod: String = "",
    val meetPlace: String? = null,
    val meetTime: String? = null,
    val status: String = "대기중",
    val createdAt: Timestamp? = null
)
