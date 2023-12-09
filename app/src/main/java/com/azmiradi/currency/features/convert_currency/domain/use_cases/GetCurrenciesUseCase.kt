package com.azmiradi.currency.features.convert_currency.domain.use_cases

import com.azmiradi.currency.common.domain.models.Resource
import com.azmiradi.currency.common.exception.BaseException
import com.azmiradi.currency.features.convert_currency.domain.repositories.ICurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(private val repository: ICurrencyRepository) {
     operator fun invoke() =
        flow {
            emit(Resource.Loading)
            try {
                val data = repository.getLatestExchangeRate()
                emit(Resource.Success(data.rates.keys))
            } catch (ex: Exception) {
                if (ex is BaseException)
                    emit(Resource.Failure(ex))
                else
                    emit(Resource.Failure(BaseException.Unknown(ex.message)))
            }
        }.flowOn(Dispatchers.IO)

}