package com.zaripov.waveaccesstestwork.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ProfileView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun displayAlert(message: String)
}
