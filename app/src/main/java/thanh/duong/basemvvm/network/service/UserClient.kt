package thanh.duong.basemvvm.network.service

import android.util.Log
import thanh.duong.basemvvm.network.middleware.Middleware

class UserClient(private val userService: UserService) : Middleware(){

    init {
        Log.d("DEBUGX", "init UserClient")
    }


    suspend fun getUser() = getResult {
        userService.getUser()
    }

    suspend fun getCryptoCurrency() = getResult {
        userService.getCryptoCurrency(0, 20)
    }
}