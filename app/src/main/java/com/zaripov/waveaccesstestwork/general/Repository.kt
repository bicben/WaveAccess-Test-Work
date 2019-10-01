package com.zaripov.waveaccesstestwork.general

import com.zaripov.waveaccesstestwork.api.ApiService
import com.zaripov.waveaccesstestwork.db.DBService
import com.zaripov.waveaccesstestwork.model.Model
import com.zaripov.waveaccesstestwork.model.User
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

    fun fetchModels(): Single<List<Model>> = apiService.getUsers()

    fun getAllModels(): Flowable<List<Model>> = dbService.getAllModels()
    fun getAllUsers(): Flowable<List<User>> = dbService.getAllusers()
    fun getModel(id: Long): Single<Model> = dbService.getModel(id)
    fun getFriends(ids: List<Long>) = dbService.getFriends(ids)

    fun insertModels(models: List<Model>): Completable = dbService.insertModels(models)

    fun deleteUsers() = dbService.deleteUsers()
}