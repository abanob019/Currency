package com.azmiradi.currency.features.convert_currency.domain.repositories

import com.azmiradi.currency.features.convert_currency.domain.model.ExchangeRate

interface ICurrencyRepository {
    suspend fun getLatestExchangeRate(queryMap: Map<String, Any>? = null): ExchangeRate
}