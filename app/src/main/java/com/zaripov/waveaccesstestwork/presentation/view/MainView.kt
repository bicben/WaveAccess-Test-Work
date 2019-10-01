package com.zaripov.waveaccesstestwork.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.zaripov.waveaccesstestwork.model.Model
import com.zaripov.waveaccesstestwork.model.User

interface MainView : MvpView{
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData(users: List<User>)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setLoading(loading: Boolean)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun displayAlert(message: String)
}