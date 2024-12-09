package com.toquete.inandout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
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
@Preview(showBackground = true)
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

@Preview
@Composable
private fun PreviewSummary() {
    MaterialTheme {
        Summary(
            expenses = "R$ 25,00",
            incomes = "R$ 0,00",
            balance = "R$ 25,00"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BalanceItemPreview() {
    MaterialTheme {
        BalanceItem(
            item = Item(
                date = "2021-01-01",
                type = "Saída",
                category = "Alimentação",
                description = "Almoço no restaurante",
                amount = 25.0,
                status = "Pago"
            )
        )
    }
}