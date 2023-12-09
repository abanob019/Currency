package com.azmiradi.currency.features.convert_currency.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azmiradi.currency.common.domain.models.Resource
import com.azmiradi.currency.common.exception.BaseException
import com.azmiradi.currency.features.convert_currency.domain.use_cases.CurrencyConverterUseCase
import com.azmiradi.currency.features.convert_currency.domain.use_cases.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class CurrencyConverterState(
    val currencySym: List<String>? = null,
    val convertedRate: String? = null,
    val error: BaseException? = null,
    val loading: Boolean = false
)

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val converterUseCase: CurrencyConverterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CurrencyConverterState())
    val state: StateFlow<CurrencyConverterState> get() = _state.asStateFlow()

    init {
        getCurrencies()
    }

     fun getCurrencies() {
        getCurrenciesUseCase().onEach {
            when (it) {
                is Resource.Failure -> _state.value = CurrencyConverterState(error = it.exception)
                Resource.Loading -> _state.value = CurrencyConverterState(loading = true)
                is Resource.Success -> _state.value =
                    CurrencyConverterState(currencySym = it.model.toList())
            }
        }.launchIn(viewModelScope)
    }

    fun convertCurrency(fromCurrency: String?, toCurrency: String?, amount: Double) {
        if (fromCurrency == null || toCurrency == null)
        {
            _state.value = CurrencyConverterState(error = BaseException.Unknown("Data Not Complete"))
            return
        }
        converterUseCase(fromCurrency, toCurrency, amount).onEach {
            when (it) {
                is Resource.Failure -> _state.value = CurrencyConverterState(error = it.exception)
                Resource.Loading -> _state.value = CurrencyConverterState(loading = true)
                is Resource.Success -> _state.value =
                    CurrencyConverterState(convertedRate = it.model.toString())
            }
        }.launchIn(viewModelScope)
    }
}