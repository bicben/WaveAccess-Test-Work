package com.zaripov.waveaccesstestwork.general

import com.zaripov.waveaccesstestwork.api.ApiService
import com.zaripov.waveaccesstestwork.db.DBService
import com.zaripov.waveaccesstestwork.db.WaveAccessDao
import com.zaripov.waveaccesstestwork.model.Model
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call

class Repository(
    private val apiService: ApiService,
    private val dbService: DBService
) {
    companion object {
        const val BASE_URL = "https://dl.dropboxusercontent.com/s/s8g63b149tnbg8x/"
        const val JSON = "users.json"
    }

    fun fetchUsers(): Single<List<Model>> = apiService.getUsers()
    fun fetchRaw() : Call<String> = apiService.getRaw()

    fun getAllUsers(): Flowable<List<Model>> = dbService.getAllUsers()
    fun getUser(id: Long): Single<Model> = dbService.getUser(id)

    fun insertModels(models: List<Model>): Completable = dbService.insertModels(models)

    fun deleteUsers() = dbService.deleteUsers()
}