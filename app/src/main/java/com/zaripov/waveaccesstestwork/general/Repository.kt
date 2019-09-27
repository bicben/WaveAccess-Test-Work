package com.zaripov.waveaccesstestwork.general

import com.zaripov.waveaccesstestwork.api.ApiService
import com.zaripov.waveaccesstestwork.model.Model
import io.reactivex.Single
import retrofit2.Call

class Repository(
    private val apiService: ApiService
) {
    companion object {
        const val BASE_URL = "https://dl.dropboxusercontent.com/s/s8g63b149tnbg8x/"
        const val JSON = "users.json"
    }

    fun fetchUsers(): Single<List<Model>> = apiService.getUsers()
    fun fetchRaw() : Call<String> = apiService.getRaw()
}