package io.gigiperih.githubuser.users

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gigiperih.githubuser.domain.usecase.GetUsersUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    ViewModel() {

    fun load() { Timber.d("grigi > load")
        getUsersUseCase.execute(
            onSuccess = {
                Timber.d("grigi > ${it.map { it }}")
            },
            onError = {
                Timber.d("grigi")
            },
        )
    }
}