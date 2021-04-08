package io.gigiperih.githubuser.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.gigiperih.githubuser.domain.usecase.SearchUsersUseCase
import io.gigiperih.githubuser.utils.RxSchedulerRule
import io.gigiperih.githubuser.utils.testObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class UsersViewModelTest {
    private lateinit var objectUnderTest: UsersViewModel

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

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

    @Test
    fun `verify UsersViewModel returns UsersState_OnInitialLoading`() {
        val observer = objectUnderTest.usersState.testObserver()
        Truth.assertThat(observer).isNotNull()
        objectUnderTest.findUsers("q", 1)

        Truth.assertThat(observer.observedValues).apply {
            isNotNull()
        }
    }
}