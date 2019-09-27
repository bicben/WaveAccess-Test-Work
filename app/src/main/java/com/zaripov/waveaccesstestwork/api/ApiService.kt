package com.zaripov.waveaccesstestwork.api

import com.zaripov.waveaccesstestwork.model.Model
import io.reactivex.Single

class ApiService(private val api:WaveAccessApi) {
    fun getUsers()= api.getUsers()
    fun getRaw() = api.getRaw()
}