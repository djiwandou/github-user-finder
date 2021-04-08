package io.gigiperih.githubuser.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gigiperih.githubuser.domain.entity.ResponseUser
import io.gigiperih.githubuser.domain.usecase.SearchUsersUseCase
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val searchUsersUseCase: SearchUsersUseCase) :
    ViewModel() {
    val usersState: MutableLiveData<UsersState<ResponseUser>> = MutableLiveData()

    fun findUsers(query: String, page: Int) {
        searchUsersUseCase.apply {
            this.query = query
            this.page = page
            execute(
                onStart = {
                    if (page == 1) {
                        usersState.postValue(UsersState.OnInitialLoading())
                    } else {
                        usersState.postValue(UsersState.OnLoadMore())
                    }
                },
                onSuccess = {
                    if (it.items.isNotEmpty()) {
                        usersState.postValue(UsersState.OnSuccess(it))
                    } else {
                        if (page > 1) {
                            usersState.postValue(UsersState.OnCompletelyLoaded())
                        } else {
                            usersState.postValue(
                                UsersState.OnError("Found nuffin, try another keywords :)")
                            )
                        }
                    }
                },
                onError = {
                    usersState.postValue(UsersState.OnError(it.localizedMessage))
                },
            )
        }
    }
}