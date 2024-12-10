package com.toquete.inandout

data class State(
    val isLoading: Boolean = true,
    val transactions: List<Transaction> = emptyList(),
    val expenses: String = "",
    val incomes: String = "",
    val balance: String = ""
)
