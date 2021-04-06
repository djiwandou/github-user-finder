package io.gigiperih.githubuser.domain.usecase

import io.gigiperih.githubuser.domain.arch.SingleUseCase
import io.gigiperih.githubuser.domain.entity.User
import io.gigiperih.githubuser.domain.repository.UsersRepository

class GetUsersUseCase constructor(private val repository: UsersRepository) :
    SingleUseCase<List<User>>() {

    override fun buildSingleUseCase() = repository.getUsers(param = "gigi")
}