package com.vn.onekiwi.network.service

import android.util.Log
import com.vn.onekiwi.network.middleware.Middleware

class UserClient(private val userService: UserService) : Middleware(){

    init {
        Log.d("DEBUGX", "init UserClient")
    }

    suspend fun getUser() = getResult {
        userService.getUser()
    }

}