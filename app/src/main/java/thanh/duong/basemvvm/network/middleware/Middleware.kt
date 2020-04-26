package thanh.duong.basemvvm.network.middleware

import retrofit2.HttpException
import retrofit2.Response


open class Middleware {

    protected suspend fun <T> getResult(request: suspend () -> Response<T>): Result<T> {
        try {
            val response = request()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.error("Server response error")
                }
            } else {
                Result.error("${response.code()} ${response.message()}")
            }
        } catch (ex: Exception) {
            val errorMessage = ex.message ?: ex.toString()
            return Result.error("Exception called failed with message $errorMessage")
        } catch (ex: HttpException){
            val errorMessage = ex.message ?: ex.toString()
            return Result.error("HttpException failed with message $errorMessage")
        } catch (ex: Throwable){
            val errorMessage = ex.message ?: ex.toString()
            return Result.error("Throwable failed with message $errorMessage")
        }
    }
}