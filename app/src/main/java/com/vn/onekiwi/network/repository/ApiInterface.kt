package com.vn.onekiwi.network.repository

import com.vn.onekiwi.model.User
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("core/collections/")
    suspend fun getCollections(): Response<User>

}