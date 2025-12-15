package com.example.book.model

import com.google.firebase.Timestamp

data class Message(
    val id: String = "",
    val senderId: String = "",
    val type: String = "text",   // text | proposal
    val text: String = "",
    val exchangeId: String = "",
    val proposalData: Map<String, Any>? = null,
    val createdAt: Timestamp? = null
)
