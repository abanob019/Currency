package com.azmiradi.currency.common.utilities

import com.azmiradi.currency.common.exception.BaseException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <Domain> executeNetworkRequest(networkRequest: suspend () -> Response<Domain>): Domain{
    try {
        val response = networkRequest()
        if (response.isSuccessful) {
            return response.body()!!
        }
        throw BaseException.Server.InternalServerError(response.code(), message = response.errorBody()?.string())
    } catch (e: IOException) {
        throw BaseException.Network.Retrial("No Internet Connection")
    } catch (e: HttpException) {
        throw BaseException.Network.Retrial("No Internet Connection")
    } catch (e: Exception) {
        throw BaseException.Network.Unhandled(e.message)
    }
}