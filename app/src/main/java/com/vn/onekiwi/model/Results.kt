package com.vn.onekiwi.model

import androidx.annotation.Keep

@Keep
sealed class Results<out T> {

    data class Success<out T>(val data: T ?= null ) : Results<T>()

    data class Error<out T>(val message: String ?= null) : Results<T>()

    class Loading<out T> : Results<T>()

}