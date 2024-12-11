package com.toquete.inandout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlin.reflect.KClass

fun NavGraphBuilder.homeScreen(onAddTransactionButtonClick: () -> Unit) {
    composable("home") {
        HomeRoute(onAddTransactionButtonClick)
    }
}

@Composable
fun HomeRoute(onAddTransactionButtonClick: () -> Unit) {
    val viewModel: DefaultViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return DefaultViewModel() as T
            }
        }
    )
    val state by viewModel.state.collectAsStateWithLifecycle()

    Home(state, onAddTransactionButtonClick)
}

@Composable
fun Home(
    state: State,
    onAddTransactionButtonClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Summary(
                modifier = Modifier.padding(16.dp),
                expenses = state.expenses,
                incomes = state.incomes,
                balance = state.balance
            )
            ItemList(state.transactions)
        }
        FloatingActionButton(
            onClick = onAddTransactionButtonClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add transaction")
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