package io.gigiperih.githubuser.domain.entity

import com.alibaba.fastjson.annotation.JSONField

data class User(
    @JSONField(name = "login") val userName: String,
    @JSONField(name = "avatar_url") val avatar: String,
    @JSONField(name = "html_url") val url: String
)