package com.zaripov.waveaccesstestwork.db

import android.arch.persistence.room.*
import com.zaripov.waveaccesstestwork.adapters.MyTypeConverter
import com.zaripov.waveaccesstestwork.model.Friend
import com.zaripov.waveaccesstestwork.model.Model
import com.zaripov.waveaccesstestwork.model.Tag
import com.zaripov.waveaccesstestwork.model.User
import io.reactivex.Completable

@Database(entities = [User::class, Friend::class, Tag::class], version = 1)
@TypeConverters(MyTypeConverter::class)
abstract class WaveAccessDB : RoomDatabase() {
    abstract val dao: WaveAccessDao
}