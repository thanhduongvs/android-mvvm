package com.vn.onekiwi.network.repository

import android.content.Context
import com.vn.onekiwi.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    fun createRetrofit(context: Context): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient(context))
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    private fun getOkHttpClient(context: Context): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        /*
        val sharedPref = PrefHelper.customPrefs(context, Constants.PREF_NAME)

        val token = sharedPref.getString(Constants.PREF_AUTH_TOKEN, "")
        val tokenStr = if (token != "") {
            "Token $token"
        } else {
            ""
        }*/

        httpClient.connectTimeout(25, TimeUnit.SECONDS)
        httpClient.readTimeout(25, TimeUnit.SECONDS)

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader(
                    "Content-Type",
                    "application/json"
                )/*
                .addHeader(
                    "Authorization",
                    tokenStr
                )*/
            val request = requestBuilder.build()
            return@addInterceptor chain.proceed(request)
        }
        return httpClient.build()
    }
}