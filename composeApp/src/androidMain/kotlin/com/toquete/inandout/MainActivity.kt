package com.toquete.inandout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.math.BigDecimal

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
    Home(
        state = State(
            isLoading = false,
            transactions = listOf(
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
            )
        ),
        onAddTransactionButtonClick = {},
        onDeleteClick = {}
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
            ),
            onDeleteClick = {}
        )
    }
}