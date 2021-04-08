package io.gigiperih.githubuser.users

sealed class UsersState<out T> {
    class OnInitialLoading<out T> : UsersState<T>()
    class OnLoadMore<out T> : UsersState<T>()
    data class OnSuccess<out T>(val data: T?) : UsersState<T>()
    class OnCompletelyLoaded<out T> : UsersState<T>()
    class OnError<out T>(val message: String?) : UsersState<T>()
}