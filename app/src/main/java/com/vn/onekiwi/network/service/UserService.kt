package com.vn.onekiwi.network.service

import retrofit2.Response
import retrofit2.http.GET
import com.vn.onekiwi.model.User

interface UserService {

    @GET("users/thanhduongvs")
    suspend fun getUser(): Response<User>

}