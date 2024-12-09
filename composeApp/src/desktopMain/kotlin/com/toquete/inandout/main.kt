package com.toquete.inandout

import android.app.Application
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize

fun main() = application {
    FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {
        val storage = mutableMapOf<String, String>()
        override fun clear(key: String) {
            storage.remove(key)
        }

        override fun log(msg: String) {
            println(msg)
        }

        override fun retrieve(key: String): String? {
            return storage[key]
        }

        override fun store(key: String, value: String) {
            storage[key] = value
        }
    })

    val firebaseOptions = FirebaseOptions(
        projectId = "inandout-4a9ca",
        applicationId = "1:845684420623:web:38350d4ecf94719c4e337a",
        apiKey = "AIzaSyBc0L2xDryLdmhUBz_iCxm800dbVXAX98A"
    )

    Firebase.initialize(Application(), firebaseOptions)

    Window(
        onCloseRequest = ::exitApplication,
        title = "InAndOut",
    ) {
        App()
    }
}

@Composable
@Preview
fun PreviewApp() {
    ItemList(
        listOf(
            Item(
                date = "2021-01-01",
                type = "Entrada",
                category = "Alimentação",
                description = "Almoço no restaurante",
                amount = 25.0,
                status = "Pago"
            )
        )
    )
}