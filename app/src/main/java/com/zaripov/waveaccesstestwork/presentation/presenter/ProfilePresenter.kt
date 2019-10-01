package com.zaripov.waveaccesstestwork.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.zaripov.waveaccesstestwork.general.Repository
import com.zaripov.waveaccesstestwork.general.WaveAccessApp
import com.zaripov.waveaccesstestwork.model.User
import com.zaripov.waveaccesstestwork.presentation.view.ProfileView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {
    companion object {
        const val TAG = "ProfilePresenter"
    }

    @Inject
    lateinit var repo: Repository

    lateinit var user: User
        private set
    lateinit var friends: List<User>
        private set

    private val disposables = CompositeDisposable()


    fun loadAndDisplayUser(id: Long) {
        disposables.add(
            repo.getModel(id)
                .subscribeOn(Schedulers.io())
                .flatMap { model ->
                    Log.i(TAG, "model queried: ${model.user.name}")
                    user = model.user
                    repo.getFriends(model.friends.map { friend -> friend.friendId })
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe({ friends ->
                    Log.i(TAG, "friends queried: ${friends.map { it.name }}")
                    this.friends = friends
                    viewState.setData()
                }, { throwable ->
                    Log.e(TAG, throwable.toString())
                    viewState.displayAlert(throwable.toString())
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    init {
        WaveAccessApp.instance.appComponent
            .inject(this)
    }
}
