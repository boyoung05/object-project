package com.example.project.domain

import com.example.project.data.BookRepository

// Use Case (앱 비즈니스 로직 담당)
class GetBookListUseCase(
    private val repository: BookRepository = BookRepository()
) {
    operator fun invoke(): List<BookEntity> {
        return repository.getBookList().map {
            BookEntity(
                id = it.id,
                title = it.title,
                author = it.author
            )
        }
    }
}


