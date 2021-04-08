package io.gigiperih.githubuser.data.utils

import io.gigiperih.githubuser.data.source.remote.GithubUserService
import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.gigiperih.githubuser.domain.entity.User
import io.reactivex.rxjava3.core.Single

class FakeGithubUserService : GithubUserService {
    override fun getUsers(query: String, since: Int, perPage: Int): Single<ResponseUser> {
        return Single.just(
            ResponseUser(
                2,
                listOf(
                    User(1L, "1", "av1", "ur1"),
                    User(2L, "2", "av2", "ur2")
                )
            )
        )
    }
}

val fakeResponse = ResponseUser(
    2,
    listOf(
        User(1L, "1", "av1", "ur1"),
        User(2L, "2", "av2", "ur2")
    )
)