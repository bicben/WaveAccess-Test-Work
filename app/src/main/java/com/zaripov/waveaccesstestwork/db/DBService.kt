package com.zaripov.waveaccesstestwork.db

import com.zaripov.waveaccesstestwork.model.Model
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class DBService(private val dao: WaveAccessDao) {
    fun getAllUsers(): Flowable<List<Model>> = dao.getModels()
    fun getUser(id: Long): Single<Model> = dao.getModel(id)

    fun insertModels(models: List<Model>) = Completable.fromAction {
        dao.insert(models)
    }

    fun deleteUsers() = Completable.fromAction {
        dao.deleteAllUsers()
    }
}