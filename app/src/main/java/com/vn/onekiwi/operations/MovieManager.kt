package com.vn.onekiwi.operations

import android.content.Context
import com.vn.onekiwi.model.User
import retrofit2.Response

class MovieManager(private val context: Context): MovieInterface {

    private val service: MovieService? = null
    override suspend fun getUser(): Response<User> {
        TODO("Not yet implemented")
    }


}