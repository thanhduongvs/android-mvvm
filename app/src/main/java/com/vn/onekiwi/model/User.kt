package com.vn.onekiwi.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") var login: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("blog") var blog: String? = null
)