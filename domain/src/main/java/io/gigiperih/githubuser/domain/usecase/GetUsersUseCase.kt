package io.gigiperih.githubuser.domain.usecase

import io.gigiperih.githubuser.domain.arch.SingleUseCase
import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.gigiperih.githubuser.domain.repository.UsersRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UsersRepository) :
    SingleUseCase<ResponseUser>() {

    override fun buildSingleUseCase() = repository.getUsers(param = "gigi")
}