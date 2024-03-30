package com.example.userlist.api

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.Header

class AuthenticatorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
      val newRequest =  chain.request().newBuilder().addHeader("Accept", "application/vnd.github+json")
            .addHeader("Authorization","Bearer <YOUR_TOKEN>")
            .addHeader("X-GitHub-Api-Version","2022-11-28").build()
       return chain.proceed(newRequest)

    }
}