package io.gigiperih.githubuser.data.source.remote

import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubUserService {
    @GET("search/users")
    fun getUsers(
        @Query("q") query: String,
        @Query("page") since: Int,
        @Query("per_page") perPage: Int
    ): Single<ResponseUser>
}