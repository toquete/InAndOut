package com.toquete.inandout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Summary(
    expenses: String,
    incomes: String,
    balance: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {}
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Despesas")
                Text(expenses)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Saldo")
                Text(balance)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Receitas")
                Text(incomes)
            }
        }
    }
}