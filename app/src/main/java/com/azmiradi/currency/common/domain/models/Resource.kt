package com.azmiradi.currency.common.domain.models

import com.azmiradi.currency.common.exception.BaseException


sealed class Resource<out Model> {
    data object Loading : Resource<Nothing>()
    data class Success<out Model>(val model: Model) : Resource<Model>()
    data class Failure(val exception: BaseException) : Resource<Nothing>()
}