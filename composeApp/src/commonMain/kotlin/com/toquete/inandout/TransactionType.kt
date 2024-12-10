package com.toquete.inandout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TransactionType(val type: String) {
    @SerialName("income")
    INCOME("income"),
    @SerialName("expense")
    EXPENSE("expense")
}