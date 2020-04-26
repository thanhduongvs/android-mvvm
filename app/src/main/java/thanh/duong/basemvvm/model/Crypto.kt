package thanh.duong.basemvvm.model

import com.google.gson.annotations.SerializedName

data class Crypto(
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("symbol") var symbol: String? = null
)