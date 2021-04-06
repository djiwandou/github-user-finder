package io.gigiperih.githubuser.users

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gigiperih.githubuser.domain.usecase.GetUsersUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    ViewModel() {
    init {
        load()
    }

    private fun load() {
        getUsersUseCase.execute(
            onSuccess = {
                Timber.d("data > ${it.map { it }}")
            },
            onError = {
                Timber.d("error")
            },
        )
    }
}