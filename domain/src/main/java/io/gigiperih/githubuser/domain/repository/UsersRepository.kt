package io.gigiperih.githubuser.domain.repository

import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.reactivex.rxjava3.core.Single

interface UsersRepository {
    fun getUsers(param: String, since: Int, perPage: Int): Single<ResponseUser>
}