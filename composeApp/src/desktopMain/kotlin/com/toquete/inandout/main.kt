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
import java.math.BigDecimal

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
            Transaction(
                date = "2021-01-01",
                category = Category(
                    id = "rent",
                    name = "Alimentação",
                    type = TransactionType.EXPENSE,
                    isRecurrent = false
                ),
                description = "Almoço no restaurante",
                amount = BigDecimal("25.0"),
                status = Status.PAID
            )
        ),
        onDeleteClick = {}
    )
}