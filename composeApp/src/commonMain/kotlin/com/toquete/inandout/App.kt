package com.toquete.inandout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val viewModel: DefaultViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Summary(
                modifier = Modifier.padding(16.dp),
                expenses = state.expenses,
                incomes = state.incomes,
                balance = state.balance
            )
            ItemList(state.transactions)
        }
    }
}

@Composable
fun ItemList(transactions: List<Transaction>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(transactions) { transaction ->
            BalanceItem(transaction = transaction)
        }
    }
}