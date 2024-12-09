package com.toquete.inandout

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val date: String,
    val type: String,
    val category: String,
    val description: String,
    val amount: Double,
    val status: String
)
