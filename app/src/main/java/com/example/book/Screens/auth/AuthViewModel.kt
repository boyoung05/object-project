package com.example.book.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

class AuthViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val db = Firebase.firestore

    fun register(
        email: String,
        password: String,
        nickname: String,
        school: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                // 🔥 Firestore 사용자 정보 저장
                val uid = auth.currentUser!!.uid

                val userData = mapOf(
                    "email" to email,
                    "nickname" to nickname,
                    "school" to school,
                    "uid" to uid
                )

                db.collection("users").document(uid)
                    .set(userData)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { e -> onError(e.message ?: "Firestore 저장 실패") }
            }
            .addOnFailureListener { e ->
                onError(e.message ?: "회원가입 실패")
            }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onError(e.message ?: "로그인 실패") }
    }

    fun isLoggedIn(): Boolean = auth.currentUser != null
}
