package io.gigiperih.githubuser.data.source.remote

import io.gigiperih.githubuser.domain.entity.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubUserService {
    @GET("search/users?q=gigi")
    fun getUsers(): Single<List<User>>
}