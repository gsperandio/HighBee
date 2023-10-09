package com.br.highbee.models

import java.math.BigDecimal
import kotlinx.serialization.*

@Serializable
data class ProductsBag(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("desc") val desc: String,
    @SerialName("img") val img: String,
    @SerialName("price") val price: Double,
    @SerialName("qtd") var qtd: Int
)
