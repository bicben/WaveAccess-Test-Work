package com.zaripov.waveaccesstestwork.di.modules

import android.arch.persistence.room.Room
import com.zaripov.waveaccesstestwork.db.WaveAccessDB
import com.zaripov.waveaccesstestwork.db.WaveAccessDao
import com.zaripov.waveaccesstestwork.general.WaveAccessApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDao(db: WaveAccessDB): WaveAccessDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideDB(app: WaveAccessApp): WaveAccessDB{
        return Room.databaseBuilder(app.applicationContext, WaveAccessDB::class.java, "WaveAccessDatabase").build()
    }
}