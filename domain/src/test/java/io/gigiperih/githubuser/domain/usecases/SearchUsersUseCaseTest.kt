package io.gigiperih.githubuser.domain.usecases

import com.google.common.truth.Truth
import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.gigiperih.githubuser.domain.entity.User
import io.gigiperih.githubuser.domain.repository.UsersRepository
import io.gigiperih.githubuser.domain.usecase.SearchUsersUseCase
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchUsersUseCaseTest {
    private lateinit var objectUnderTest: SearchUsersUseCase

    @Mock
    private lateinit var mockedUserRepo: UsersRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        objectUnderTest = SearchUsersUseCase(mockedUserRepo)
    }

    @Test
    fun `verify SearchUsersUseCase is not null`() {
        Truth.assertThat(objectUnderTest).isNotNull()
    }

    @Test
    fun `verify SearchUsersUseCase returns valid use cases`() {
        val fakeResponse = ResponseUser(
            2,
            listOf(
                User(1L, "1", "av1", "ur1"),
                User(2L, "2", "av2", "ur2")
            )
        )

        Mockito.`when`(
            objectUnderTest.buildSingleUseCase()
        ).thenReturn(Single.just(fakeResponse))

        Truth.assertThat(objectUnderTest.buildSingleUseCase().blockingGet()).apply {
            isNotNull()
            isEqualTo(fakeResponse)
        }
    }
}