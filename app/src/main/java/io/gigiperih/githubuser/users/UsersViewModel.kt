package io.gigiperih.githubuser.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.gigiperih.githubuser.domain.usecase.SearchUsersUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val searchUsersUseCase: SearchUsersUseCase) :
    ViewModel() {

    val users: MutableLiveData<ResponseUser> = MutableLiveData()

    fun findUsers(query: String) {
        searchUsersUseCase.apply {
            this.query = query
            execute(
                onStart = {
                    Timber.d("grigi > starting")
                },
                onSuccess = {
                    Timber.d("grigi > success")
                    users.postValue(it)
                },
                onError = {
                    Timber.d("grigi > error: ${it.printStackTrace()}")
                },
            )
        }
    }
}