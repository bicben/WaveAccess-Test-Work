package com.zaripov.waveaccesstestwork.general

import android.app.Application
import com.zaripov.waveaccesstestwork.R
import com.zaripov.waveaccesstestwork.di.AppComponent
import com.zaripov.waveaccesstestwork.di.DaggerAppComponent
import com.zaripov.waveaccesstestwork.di.modules.AppModule

class WaveAccessApp : Application() {
    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: WaveAccessApp
            private set
    }

    var name = ""
        get() = resources.getString(R.string.app_name)
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}