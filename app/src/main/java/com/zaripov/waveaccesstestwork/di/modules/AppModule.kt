package com.zaripov.waveaccesstestwork.di.modules

import com.zaripov.waveaccesstestwork.general.WaveAccessApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app:WaveAccessApp) {
    @Provides
    @Singleton
    internal fun provideApp(): WaveAccessApp{
        return app
    }
}