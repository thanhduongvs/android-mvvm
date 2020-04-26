package thanh.duong.basemvvm.network.repository

import android.util.Log
import thanh.duong.basemvvm.network.service.UserClient

class UserRepo(private val userClient: UserClient) : BaseRepo(){

    init {
        Log.d("DEBUGX", "init UserRepo")
    }

    fun getUser() = makeRequest {
        userClient.getUser()
    }

    fun getCryptoCurrency() = makeRequest {
        userClient.getCryptoCurrency()
    }
}