package io.gigiperih.githubuser.users

import com.google.common.truth.Truth
import io.gigiperih.githubuser.domain.usecase.SearchUsersUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {
    private lateinit var objectUnderTest: UsersViewModel

    @Mock
    private lateinit var mockedUseCase: SearchUsersUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        objectUnderTest = UsersViewModel(mockedUseCase)
    }

    @Test
    fun `verify UsersViewModel is not null`() {
        Truth.assertThat(objectUnderTest).isNotNull()
    }
}