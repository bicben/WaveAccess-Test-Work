package com.zaripov.waveaccesstestwork.db

import android.arch.persistence.room.*
import com.zaripov.waveaccesstestwork.model.Friend
import com.zaripov.waveaccesstestwork.model.Model
import com.zaripov.waveaccesstestwork.model.Tag
import com.zaripov.waveaccesstestwork.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
abstract class WaveAccessDao {
    @Transaction
    open fun insert(models: List<Model>) {
        for (model in models) {
            insertUser(model.user)
            insertFriends(model.friends)
            insertTags(model.tags)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFriends(friends: List<Friend>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTags(tag: List<Tag>)

    @Transaction @Query("SELECT * FROM User")
    abstract fun getModels(): Flowable<List<Model>>

    @Transaction @Query("SELECT * FROM User WHERE id = :id")
    abstract fun getModel(id: Long): Single<Model>

    @Query("DELETE from User")
    abstract fun deleteAllUsers()

    @Query("DELETE from Friend")
    abstract fun deleteAllFriends()

    @Query("DELETE from Tag")
    abstract fun deleteAllTags()
}