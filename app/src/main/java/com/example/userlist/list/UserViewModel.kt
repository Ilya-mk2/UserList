package com.example.userlist.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userlist.api.ApiHolder
import com.example.userlist.data.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserViewModel : ViewModel() {

    val screenState = MutableLiveData<UsersScreenState>()

    fun loadUsers() {
        screenState.value = UsersScreenState.Loading

        val disposable = ApiHolder.usersAPI.getAllUsers(

        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                screenState.value = UsersScreenState.Success(it)

            }, {
                screenState.value = UsersScreenState.Error(it)
            })
    }

}

sealed interface UsersScreenState {

    data object Loading : UsersScreenState

    data class Success(val list: List<User>) : UsersScreenState

    data class Error(val exception: Throwable) : UsersScreenState
}