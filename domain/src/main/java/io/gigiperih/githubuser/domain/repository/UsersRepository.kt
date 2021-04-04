package io.gigiperih.githubuser.domain.repository

import io.gigiperih.githubuser.domain.entity.User
import io.reactivex.rxjava3.core.Single

interface UsersRepository {
    fun getUsers(param: String): Single<List<User>>
}