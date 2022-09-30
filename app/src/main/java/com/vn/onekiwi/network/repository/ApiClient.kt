package com.vn.onekiwi.network.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody

class ApiClient(private val api: ApiInterface) : BaseApiClient() {

    suspend fun getCollections() = getResult {
        api.getCollections()
    }

}