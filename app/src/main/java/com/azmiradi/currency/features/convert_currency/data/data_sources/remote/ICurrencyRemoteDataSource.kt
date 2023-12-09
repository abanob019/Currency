package com.azmiradi.currency.features.convert_currency.data.data_sources.remote

import com.azmiradi.currency.features.convert_currency.data.models.dto.ExchangeRateDto

interface ICurrencyRemoteDataSource {
    suspend fun getLatestExchangeRate(queryMap: Map<String,Any>? = null): ExchangeRateDto
}