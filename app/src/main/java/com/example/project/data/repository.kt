package com.example.project.data

class BookRepository(
    private val dataSource: BookDataSource = LocalBookDataSource()
) {
    fun getBookList(): List<Book> {
        return dataSource.getBooks()
    }
}


