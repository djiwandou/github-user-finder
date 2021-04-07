package io.gigiperih.githubuser.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.gigiperih.githubuser.domain.usecase.GetUsersUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    ViewModel() {

    val users: MutableLiveData<ResponseUser> = MutableLiveData()

    fun load() {
        getUsersUseCase.execute(
            onSuccess = {
                users.postValue(it)
            },
            onError = {
                Timber.d("grigi ${it.printStackTrace()}")
            },
        )
    }
}