package com.zaripov.waveaccesstestwork.presentation.view

import com.arellomobile.mvp.MvpView

interface MainView : MvpView{
    fun displayText(text: String)
}