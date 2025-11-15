package com.example.project.data

// 예시 데이터 모델 (Home 화면에서 사용할 샘플 모델)
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val imageUrl: String = ""
)

