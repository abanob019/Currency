package com.azmiradi.currency.features.convert_currency.data.models.dto

import kotlinx.serialization.SerialName

data class ExchangeRateDto(
    @SerialName("success")
    val success: Boolean? = null,
    @SerialName("timestamp")
    val timestamp: Long? = null,
    @SerialName("base")
    val base: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("rates")
    val rates: Map<String, Double>? = null
)