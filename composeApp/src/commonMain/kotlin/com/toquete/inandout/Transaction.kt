package com.toquete.inandout

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

@Serializable
data class Transaction(
    @SerialName("date")
    val date: String,
    @SerialName("description")
    val description: String,
    @Serializable(with = BigDecimalSerializer::class)
    @SerialName("amount")
    val amount: BigDecimal,
    @SerialName("category")
    val category: Category,
    @SerialName("status")
    val status: Status
)

object BigDecimalSerializer : KSerializer<BigDecimal> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.DOUBLE)

    override fun deserialize(decoder: Decoder): BigDecimal {
        return BigDecimal.valueOf(decoder.decodeDouble())
    }

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeDouble(value.toDouble())
    }

}
