package com.example.userlist.detail


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userlist.api.ApiHolder
import com.example.userlist.data.DetailUser
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsViewModel : ViewModel() {

    val detailsState = MutableLiveData<DetailsScreenState>()

    fun loadDetails(login: String) {

        detailsState.value = DetailsScreenState.Loading


        val disposable = ApiHolder.usersAPI.getUser(
            login
        ).subscribeOn(Schedulers.io())
            .flatMap { user ->
                if (user.organization != null) {

                    ApiHolder.usersAPI.getOrganization(user.organization)
                        .map { list ->
                            DetailUser(
                                avatar = user.avatar,
                                login = user.login,
                                id = user.id,
                                name = user.name,
                                email = user.email,
                                followers = user.followers,
                                following = user.following,
                                created = user.created,
                                organizations = list
                            )
                        }
                } else {
                    Single.just(
                        DetailUser(
                            avatar = user.avatar,
                            login = user.login,
                            id = user.id,
                            name = user.name,
                            email = user.email,
                            followers = user.followers,
                            following = user.following,
                            created = user.created,
                            organizations = emptyList()
                        )
                    )
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                detailsState.value = DetailsScreenState.Success(it)
            }, {
                detailsState.value = DetailsScreenState.Error(it)
                it.printStackTrace()
            })
    }
}


sealed interface DetailsScreenState {


    data object Loading : DetailsScreenState

    data class Success(val user: DetailUser) : DetailsScreenState

    data class Error(val exception: Throwable) : DetailsScreenState
}