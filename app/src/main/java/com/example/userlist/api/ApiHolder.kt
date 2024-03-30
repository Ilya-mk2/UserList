package com.example.userlist.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiHolder {
    private const val BASE_URL = " https://api.github.com"
    val usersAPI by lazy {
        val httpClient = OkHttpClient.Builder().addInterceptor(AuthenticatorInterceptor()).build()
        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL).build()

        retrofit.create(UsersAPI::class.java)

    }
}