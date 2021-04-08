package io.gigiperih.githubuser.data.repository

import io.gigiperih.githubuser.data.source.remote.GithubUserService
import io.gigiperih.githubuser.domain.repository.UsersRepository


class UsersRepositoryImpl(private val githubUserService: GithubUserService) : UsersRepository {
    override fun getUsers(param: String, since: Int, perPage: Int) =
        githubUserService.getUsers(query = param, since = since, perPage = perPage)
}