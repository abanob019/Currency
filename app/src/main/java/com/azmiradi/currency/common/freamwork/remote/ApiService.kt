package com.azmiradi.currency.common.freamwork.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {
    @GET("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun <ResponseBody> get(
        @Path(value = "pathUrl", encoded = true) pathUrl: String,
        @HeaderMap headerMap: Map<String, Any> = emptyMap(),
        @QueryMap queryParams: Map<String, Any> = emptyMap(),
    ): Response<ResponseBody>
}