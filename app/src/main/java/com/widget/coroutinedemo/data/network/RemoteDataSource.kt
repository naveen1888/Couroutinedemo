package com.widget.coroutinedemo.data.network

import com.widget.coroutinedemo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/"
        const val API_KEY = "c628672320d04a1d859e08b9f5bb6fb1";

    }

    fun <Api> buildApi(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    //.callTimeout(1, TimeUnit.MINUTES)
                    //.connectTimeout(20, TimeUnit.SECONDS)
                    //.readTimeout(30, TimeUnit.SECONDS)
//                    .addInterceptor { chain ->
//                        chain.proceed(chain.request().newBuilder().also {
//                            it.addHeader("Authorization", "Bearer $authToken")
//                        }.build())
//                    }
                    .also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}