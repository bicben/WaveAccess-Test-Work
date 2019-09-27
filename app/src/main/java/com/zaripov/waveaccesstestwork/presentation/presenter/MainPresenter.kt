package com.zaripov.waveaccesstestwork.presentation.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.zaripov.waveaccesstestwork.general.Repository
import com.zaripov.waveaccesstestwork.general.WaveAccessApp
import com.zaripov.waveaccesstestwork.presentation.view.MainView
import com.zaripov.waveaccesstestwork.ui.activity.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        //loadAndDisplayText()
        loadAndDisplayParsed()
    }

    private fun loadAndDisplayParsed() {
        Log.i(TAG, "loading a data...")
        disposables.add(
            repo.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i(TAG, "Success! Models were fetched: ${it.size}")
                    viewState.displayText(it.toString())
                },
                    {
                        Log.e(TAG, it.toString())
                    })
        )
    }

    private fun loadAndDisplayText() {
        Log.i(TAG, "loading a text")
        repo.fetchRaw().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, t.message)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i(TAG, "Success!")
                response.body()?.let { viewState.displayText(it) } ?: Log.e(
                    TAG,
                    "but text is empty..."
                )
            }
        })
    }

    init {
        WaveAccessApp.instance.appComponent
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}