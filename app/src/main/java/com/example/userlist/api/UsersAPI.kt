package com.example.userlist.api

import com.example.userlist.data.Organization
import com.example.userlist.data.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UsersAPI {
    @GET("/users")
  fun getAllUsers(
  ) : Single<List<User>>

    @GET("/users/{login}")
  fun getUser(
      @Path("login") login : String
  ) : Single<User>
  @GET("{url}")
  fun getOrganization(
    @Path(value = "url", encoded = true ) url : String
  ) : Single<List<Organization>>
}