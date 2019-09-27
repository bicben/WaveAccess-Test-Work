package com.zaripov.waveaccesstestwork.api

import com.zaripov.waveaccesstestwork.general.Repository
import com.zaripov.waveaccesstestwork.model.Model
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface WaveAccessApi {
    @GET(value = Repository.JSON)
    fun getUsers(): Single<List<Model>>

    @GET(value = Repository.JSON)
    fun getRaw(): Call<String>
}