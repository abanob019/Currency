package com.azmiradi.currency.features.convert_currency.data.models.mapper

import com.azmiradi.currency.features.convert_currency.data.models.dto.ExchangeRateDto
import com.azmiradi.currency.features.convert_currency.domain.model.ExchangeRate

fun ExchangeRateDto.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        success == true,
        date ?: "---",
        base ?: "EGP",
        rates ?: emptyMap()
    )
}