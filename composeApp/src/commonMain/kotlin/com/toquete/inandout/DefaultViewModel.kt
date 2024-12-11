package com.toquete.inandout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat

class DefaultViewModel(
    repository: DefaultRepository = DefaultRepository()
): ViewModel() {

    private val numberFormat = NumberFormat.getCurrencyInstance()

    val state = repository.getTransactions()
        .map { transactions ->
            val expenses = transactions.filter { it.category.type == TransactionType.EXPENSE }
                .sumOf { it.amount }
            val incomes = transactions.filter { it.category.type == TransactionType.INCOME }
                .sumOf { it.amount }
            val balance = incomes - expenses

            State(
                isLoading = false,
                transactions = transactions,
                expenses = numberFormat.format(expenses),
                incomes = numberFormat.format(incomes),
                balance = numberFormat.format(balance)
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = State()
        )
}