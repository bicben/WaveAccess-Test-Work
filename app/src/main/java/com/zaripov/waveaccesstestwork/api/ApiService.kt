package com.zaripov.waveaccesstestwork.api

class ApiService(private val api:WaveAccessApi) {
    fun getUsers()= api.getUsers()
}