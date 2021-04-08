package io.gigiperih.githubuser.domain.usecase

import io.gigiperih.githubuser.domain.arch.SingleUseCase
import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.gigiperih.githubuser.domain.repository.UsersRepository
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) : SingleUseCase<ResponseUser>() {
    companion object {
        const val PER_PAGE = 10
    }

    var query: String = ""
    var page: Int = 1

    override fun buildSingleUseCase() =
        repository.getUsers(param = query, since = page, perPage = PER_PAGE)
}