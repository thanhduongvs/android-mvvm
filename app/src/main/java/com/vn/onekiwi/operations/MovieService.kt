package com.vn.onekiwi.operations

import com.vn.onekiwi.model.User
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("users/thanhduongvs")
    suspend fun getUser(): Response<User>
}
