package com.vn.onekiwi.network.repository

import com.vn.onekiwi.BuildConfig
import com.vn.onekiwi.model.Results
import retrofit2.Response

open class BaseApiClient {

    protected suspend fun <T> getResult(request: suspend () -> Response<T>): Results<T> {
        try {
            val response = request()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Results.Success(body)
                } else {
                    Results.Error("Server response error")
                }
            } else {
                Results.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            val errorMessage = e.message ?: e.toString()
            return if (BuildConfig.DEBUG) {
                Results.Error("Network called failed with message $errorMessage")
            } else {
                Results.Error("Check your internet connection!")
            }
        }
    }
}