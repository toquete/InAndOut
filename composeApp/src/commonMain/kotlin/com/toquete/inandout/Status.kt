package com.toquete.inandout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Status(val status: String) {
    @SerialName("paid")
    PAID("paid"),
    @SerialName("pending")
    PENDING("pending"),
    @SerialName("overdue")
    OVERDUE("overdue")
}