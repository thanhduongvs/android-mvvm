package thanh.duong.basemvvm.model

import com.google.gson.annotations.SerializedName

data class ListCryto(
    @SerializedName("result") var result: List<Crypto>? = null

)