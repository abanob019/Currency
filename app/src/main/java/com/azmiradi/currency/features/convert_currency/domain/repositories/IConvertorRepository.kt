package com.azmiradi.currency.features.convert_currency.domain.repositories

interface IConvertorRepository {
    suspend fun convertCurrency(fromCurrency: String, toCurrency: String, amount: String): Double
}