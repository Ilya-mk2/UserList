package com.example.userlist.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url") val avatar: String,
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name : String?,
    @SerializedName("email") val email : String?,
    @SerializedName("organizations_url") val organization : String?,
    @SerializedName("following") val following : String?,
    @SerializedName("followers") val followers : String?,
    @SerializedName("created_at") val created : String?

)