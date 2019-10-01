package com.zaripov.waveaccesstestwork.db

import com.zaripov.waveaccesstestwork.model.Model
import com.zaripov.waveaccesstestwork.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class DBService(private val dao: WaveAccessDao) {
    fun getAllModels() = dao.getModels()
    fun getAllusers() = dao.getUsers()
    fun getModel(id: Long) = dao.getModel(id)
    fun getFriends(ids: List<Long>) = dao.getFriends(ids)

    fun insertModels(models: List<Model>) = Completable.fromAction {
        dao.insert(models)
    }

    fun deleteUsers() = Completable.fromAction {
        dao.deleteAllUsers()
    }
}