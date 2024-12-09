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

    val state = repository.getItems()
        .map { items ->
            val expenses = items.filter { it.type == "Saída" }
                .sumOf { it.amount }
            val incomes = items.filter { it.type == "Entrada" }
                .sumOf { it.amount }
            val balance = incomes - expenses

            State(
                isLoading = false,
                items = items,
                expenses = numberFormat.format(expenses),
                incomes = numberFormat.format(incomes),
                balance = numberFormat.format(balance)
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = State()
        )
}