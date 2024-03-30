package com.example.userlist.data

import com.google.gson.annotations.SerializedName

data class Organization(

    @SerializedName("login") val login: String
)