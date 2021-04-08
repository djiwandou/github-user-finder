package io.gigiperih.githubuser.data.users

import com.google.common.truth.Truth
import io.gigiperih.githubuser.data.repository.UsersRepositoryImpl
import io.gigiperih.githubuser.data.utils.FakeGithubUserService
import io.gigiperih.githubuser.data.utils.fakeResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersRepositoryImplTest {
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private val objectUnderTest = UsersRepositoryImpl(FakeGithubUserService())

    @Test
    fun `verify UsersRepositoryImpl is not null`() {
        Truth.assertThat(objectUnderTest).apply {
            isNotNull()
        }
    }

    @Test
    fun `verify UsersRepositoryImpl returns valid response`() {
        val data = objectUnderTest.getUsers("param", 1, 2)

        Truth.assertThat(data.blockingGet()).apply {
            isNotNull()
            isEqualTo(fakeResponse)
        }
    }

    @Test
    fun `verify UsersRepositoryImpl returns valid items`() {
        val data = objectUnderTest.getUsers("param", 1, 2)
        val itemCount = data.blockingGet().totalCount

        Truth.assertThat(itemCount).apply {
            isNotEqualTo(0)
            isEqualTo(2)
        }

        Truth.assertThat(data.blockingGet().items[0]).apply {
            isNotNull()
            isEqualTo(fakeResponse.items[0])
        }


    }
}