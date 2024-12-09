package com.toquete.inandout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
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