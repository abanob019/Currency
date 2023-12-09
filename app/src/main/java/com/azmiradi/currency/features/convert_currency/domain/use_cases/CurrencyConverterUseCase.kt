package com.azmiradi.currency.features.convert_currency.domain.use_cases

import com.azmiradi.currency.common.domain.models.Resource
import com.azmiradi.currency.common.exception.BaseException
import com.azmiradi.currency.features.convert_currency.domain.repositories.ICurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyConverterUseCase @Inject constructor(private val repository: ICurrencyRepository) {
    operator fun invoke(fromCurrency: String, toCurrency: String, amount: Double) =
        flow {
            emit(Resource.Loading)
            try {
                val data = repository.getLatestExchangeRate()
                val fromRate = data.rates[fromCurrency]
                    ?: throw IllegalArgumentException("Invalid source currency")
                val toRate =
                    data.rates[toCurrency]
                        ?: throw IllegalArgumentException("Invalid target currency")
                val convertedAmount = amount * (toRate / fromRate)
                emit(Resource.Success(convertedAmount))
            } catch (ex: Exception) {
                if (ex is BaseException)
                    emit(Resource.Failure(ex))
                else
                    emit(Resource.Failure(BaseException.Unknown(ex.message)))
            }
        }.flowOn(Dispatchers.IO)

}