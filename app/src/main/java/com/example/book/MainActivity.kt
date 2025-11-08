package com.example.book

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.book.ui.theme.ObjectTheme
import com.example.book.BuildConfig

import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.Timestamp

// --- 지도 관련 import
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 디버그 빌드에서만 에뮬레이터 접속
        if (BuildConfig.DEBUG) {
            Firebase.firestore.useEmulator("10.0.2.2", 8080)
            Firebase.auth.useEmulator("10.0.2.2", 9099)
        }

        // 익명 로그인 → 성공 시 Firestore 쓰기/읽기 테스트
        Firebase.auth.signInAnonymously()
            .addOnSuccessListener {
                Log.d("AUTH", "anon ok: ${it.user?.uid}")
                writeAndReadTest()
            }
            .addOnFailureListener { e ->
                Log.e("AUTH", "anon fail", e)
            }

        setContent {
            ObjectTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MapScreen()
                }
            }
        }
    }

    private fun writeAndReadTest() {
        val db = Firebase.firestore
        val data = hashMapOf(
            "title" to "테스트 도서",
            "isbn" to "9790000000000",
            "createdAt" to Timestamp.now()
        )

        db.collection("books").add(data)
            .addOnSuccessListener { ref ->
                Log.d("DB", "write ok: ${ref.id}")
                ref.get().addOnSuccessListener { snap ->
                    Log.d("DB", "read ok: ${snap.data}")
                }.addOnFailureListener { e ->
                    Log.e("DB", "read fail", e)
                }
            }
            .addOnFailureListener { e ->
                Log.e("DB", "write fail", e)
            }
    }
}

@Composable
fun MapScreen() {
    val seoulCityHall = LatLng(37.5662952, 126.9779451)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(seoulCityHall, 14f)
    }

    val markerState = rememberMarkerState(position = seoulCityHall)

    GoogleMap(cameraPositionState = cameraPositionState) {
        Marker(
            state = markerState,
            title = "서울시청",
            snippet = "지도 연결 테스트"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapPreview() {
    ObjectTheme { MapScreen() }
}
