package com.zaripov.waveaccesstestwork.di

import com.zaripov.waveaccesstestwork.di.modules.AppModule
import com.zaripov.waveaccesstestwork.di.modules.RepositoryModule
import com.zaripov.waveaccesstestwork.presentation.presenter.MainPresenter
import com.zaripov.waveaccesstestwork.presentation.presenter.ProfilePresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(profilePresenter: ProfilePresenter)
}