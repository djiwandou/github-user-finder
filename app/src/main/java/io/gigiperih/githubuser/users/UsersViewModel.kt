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
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun findUsers(query: String, page: Int) {
        searchUsersUseCase.apply {
            this.query = query
            this.page = page

            Timber.d("shoutaaa $query > $page")
            execute(
                onStart = {
                    Timber.d("grigi > starting")
                    isLoading.postValue(true)
                },
                onSuccess = {
                    Timber.d("grigi > success")
                    users.value = it
                    isLoading.postValue(false)
                },
                onError = {
                    Timber.d("grigi > error: ${it.printStackTrace()}")
                },
            )
        }
    }
}