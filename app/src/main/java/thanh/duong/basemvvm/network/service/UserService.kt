package thanh.duong.basemvvm.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import thanh.duong.basemvvm.model.ListCryto
import thanh.duong.basemvvm.model.User

interface UserService {

    @GET("users/thanhduongvs")
    suspend fun getUser(): Response<User>

    @GET("api/accounts/getListcryptocurrencies")
    suspend fun getCryptoCurrency(
        @Query("start") start: Int,
        @Query("limit") limit: Int
    ): Response<ListCryto>
}