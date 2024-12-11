package com.toquete.inandout

data class TransactionState(
    val isLoading: Boolean = true,
    val categories: List<Category> = emptyList()
)
