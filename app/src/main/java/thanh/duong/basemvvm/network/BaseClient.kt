package thanh.duong.basemvvm.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

object BaseClient {

    val chatHeader = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("token", "BaseApp.prefs.authToken")
                .build()
            val response = chain.proceed(request)
            return response
        }
    }

    fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            //.addInterceptor(chatHeader)
            .addInterceptor(makeLoggingInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()
    }

    /* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
    fun createHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(120, TimeUnit.SECONDS)
        client.readTimeout(120, TimeUnit.SECONDS)
        client.writeTimeout(90, TimeUnit.SECONDS)
        client.addNetworkInterceptor(makeLoggingInterceptor())

        return client.addInterceptor {
            val original = it.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.header("Connection", "keep-alive")
            requestBuilder.header("Authorization", "BaseApp.prefs.authToken")
            val request = requestBuilder.method(original.method, original.body).build()
            return@addInterceptor it.proceed(request)
        }.build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    fun unsafeOkHttpClient(): OkHttpClient {
        val unsafeTrustManager = createUnsafeTrustManager()
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(unsafeTrustManager), null)
        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory,  unsafeTrustManager)
            //.hostnameVerifier(HostnameVerifier { hostName, sslSession -> true })
            .hostnameVerifier(HostnameVerifier { _, _ -> true })
            .build()
    }

    fun createUnsafeTrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<out X509Certificate>? {
                return emptyArray()
            }
        }
    }

    inline fun <reified T> createService(okHttpClient: OkHttpClient, url: String): T {
        Log.d("DEBUGX", "url $url")
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .client(unsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(T::class.java)
    }
}