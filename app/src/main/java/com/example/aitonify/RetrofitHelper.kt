package com.example.aitonify

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private val timeoutInSeconds: Long = 10000
    private val url = "http://34.71.128.105:5000/"

    val client = OkHttpClient.Builder()
        .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.level=HttpLoggingInterceptor.Level.BODY
        })
        .writeTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .build()

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
