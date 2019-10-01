package com.zaripov.waveaccesstestwork.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.zaripov.waveaccesstestwork.general.Repository
import com.zaripov.waveaccesstestwork.general.WaveAccessApp
import com.zaripov.waveaccesstestwork.presentation.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    companion object {
        const val TAG = "MainPresenter"
    }

    @Inject
    lateinit var repo: Repository
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadAndDisplayDataFromDB()
    }

    private fun loadAndDisplayDataFromDB() {
        Log.i(TAG, "loading a data...")
        disposables.add(
            repo.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i(TAG, "Users were in database: ${it.size}")

                    if (it.isEmpty()){
                        viewState.setLoading(true)
                        fetchUsers()
                    } else {
                        viewState.setLoading(false)
                        viewState.setData(it)
                    }
                },
                    {
                        Log.e(TAG, it.toString())
                        viewState.displayAlert(it.toString())
                    })
        )
    }

    private fun fetchUsers() {
        disposables.add(repo.fetchModels()
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                Log.i(TAG, "Models were fetched and loaded: ${it.size}")
                repo.insertModels(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i(TAG, "Success")
            },{
                Log.e(TAG, it.toString())
                viewState.displayAlert(it.toString())
            })
        )
    }

    fun deleteUsers(){
        viewState.setLoading(true)
        disposables.add(
            repo.deleteUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i(TAG, "DB was nuked successfully")
                },{
                    Log.e(TAG, it.toString())
                })
        )
    }

    init {
        WaveAccessApp.instance.appComponent
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}