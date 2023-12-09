package com.azmiradi.currency.features.convert_currency.fremowrk

import com.azmiradi.currency.common.freamwork.remote.ApiService
import com.azmiradi.currency.features.convert_currency.data.data_sources.remote.CurrencyRemoteDataSource
import com.azmiradi.currency.features.convert_currency.data.data_sources.remote.ICurrencyRemoteDataSource
import com.azmiradi.currency.features.convert_currency.data.repositories.CurrencyRepository
import com.azmiradi.currency.features.convert_currency.domain.repositories.ICurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CurrencyModule {
    @Provides
    fun provideCurrencyRemoteDataSource(apiService: ApiService): ICurrencyRemoteDataSource {
        return CurrencyRemoteDataSource(apiService)
    }

    @Provides
    fun provideCurrencyRepository(remoteDataSource: ICurrencyRemoteDataSource): ICurrencyRepository {
        return CurrencyRepository(remoteDataSource)
    }
}