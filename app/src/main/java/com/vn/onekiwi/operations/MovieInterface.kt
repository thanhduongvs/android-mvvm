package com.vn.onekiwi.operations

import com.vn.onekiwi.model.User
import retrofit2.Response

interface MovieInterface {
    suspend fun getUser(): Response<User>
}