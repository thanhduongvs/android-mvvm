package com.vn.onekiwi.network.repository

import androidx.lifecycle.liveData

import com.vn.onekiwi.model.Results
open class BaseRepo {

    protected fun <T> makeRequest(request: suspend () -> Results<T>) = liveData {
        emit(Results.Loading<T>())

        when (val response = request()) {
            is Results.Success -> {
                emit(Results.Success(response.data))
            }
            is Results.Error -> {
                emit(Results.Error(response.message))
            }
            else -> {
            }
        }
    }
}