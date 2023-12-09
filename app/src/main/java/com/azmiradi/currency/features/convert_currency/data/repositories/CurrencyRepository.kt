package com.azmiradi.currency.features.convert_currency.data.repositories

import com.azmiradi.currency.features.convert_currency.data.data_sources.remote.ICurrencyRemoteDataSource
import com.azmiradi.currency.features.convert_currency.data.models.mapper.toExchangeRate
import com.azmiradi.currency.features.convert_currency.domain.model.ExchangeRate
import com.azmiradi.currency.features.convert_currency.domain.repositories.ICurrencyRepository
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val remoteDataSource: ICurrencyRemoteDataSource) :
    ICurrencyRepository {
    override suspend fun getLatestExchangeRate(queryMap: Map<String, Any>?): ExchangeRate {
        return remoteDataSource.getLatestExchangeRate(queryMap).toExchangeRate()
    }
}