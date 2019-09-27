package com.zaripov.waveaccesstestwork.di

import com.zaripov.waveaccesstestwork.AppComponentTest
import com.zaripov.waveaccesstestwork.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface TestComponent {
    fun inject(appComponentTest: AppComponentTest)
}