package com.toquete.inandout

data class State(
    val isLoading: Boolean = true,
    val items: List<Item> = emptyList(),
    val expenses: String = "",
    val incomes: String = "",
    val balance: String = ""
)
