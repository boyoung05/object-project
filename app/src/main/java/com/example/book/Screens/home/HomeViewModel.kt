package com.example.book.Screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book.model.Book
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    init {
        loadHomeBooks()
    }

    private fun loadHomeBooks() {
        viewModelScope.launch {
            val result = db.collection("books")
                .limit(3)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Book::class.java) }

            _books.value = result
        }
    }
}


