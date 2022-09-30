package com.vn.onekiwi.network.repository

import android.util.Log
import com.vn.onekiwi.network.service.UserClient

class UserRepo(private val userClient: UserClient) : BaseRepo(){

    init {
        Log.d("DEBUGX", "init UserRepo")
    }

    fun getUser() = makeRequest {
        userClient.getUser()
    }
}