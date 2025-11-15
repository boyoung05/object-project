package com.example.project.data

// 데이터 소스 예시 (Local / Remote)
interface BookDataSource {
    fun getBooks(): List<Book>
}

// 간단한 LocalDataSource 구현
class LocalBookDataSource : BookDataSource {
    override fun getBooks(): List<Book> {
        return listOf(
            Book(1, "해리포터", "J.K. 롤링"),
            Book(2, "반지의 제왕", "톨킨"),
            Book(3, "나미야 잡화점의 기적", "히가시노 게이고")
        )
    }
}


