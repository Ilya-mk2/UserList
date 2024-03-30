package com.example.userlist.data

data class DetailUser(
    val avatar: String,
    val login: String,
    val id: String,
    val name: String?,
    val email: String?,
    val following: String?,
    val followers: String?,
    val created: String?,
    val organizations: List<Organization>
)