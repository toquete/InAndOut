package com.toquete.inandout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: TransactionType,
    @SerialName("is_recurrent")
    val isRecurrent: Boolean
)
