package com.azmiradi.currency.features.convert_currency.domain.model

data class ExchangeRate(
    val success: Boolean,
    val date: String,
    val base: String,
    val rates: Map<String, Double>
)