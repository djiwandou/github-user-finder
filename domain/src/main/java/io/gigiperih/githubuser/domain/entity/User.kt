package io.gigiperih.githubuser.domain.entity

import com.google.gson.annotations.SerializedName

data class ResponseUser(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<User>
)

data class User(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val avatar: String,
    @SerializedName("html_url") val url: String
)