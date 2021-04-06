package io.gigiperih.githubuser.domain.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val avatar: String,
    @SerializedName("html_url") val url: String
)