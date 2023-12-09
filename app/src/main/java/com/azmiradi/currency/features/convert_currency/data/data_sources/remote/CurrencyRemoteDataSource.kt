package com.azmiradi.currency.features.convert_currency.data.data_sources.remote

import com.azmiradi.currency.common.utilities.executeNetworkRequest
import com.azmiradi.currency.common.freamwork.remote.ApiService
import com.azmiradi.currency.common.utilities.CommonKeys.ACCESSES_KEY
import com.azmiradi.currency.features.convert_currency.data.models.dto.ExchangeRateDto
import javax.inject.Inject

class CurrencyRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    ICurrencyRemoteDataSource {
    override suspend fun getLatestExchangeRate(queryMap: Map<String, Any>?): ExchangeRateDto {
        return executeNetworkRequest {
            apiService.get(
                pathUrl = LATEST_END_POINT,
                queryParams = mapOf(ACCESSES_KEY to "")
            )
        }
    }

}