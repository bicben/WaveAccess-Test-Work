package com.zaripov.waveaccesstestwork.di.modules

import com.zaripov.waveaccesstestwork.db.DBService
import com.zaripov.waveaccesstestwork.db.WaveAccessDB
import com.zaripov.waveaccesstestwork.db.WaveAccessDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class DatabaseServiceModule {
@Provides
@Singleton
fun provideDatabaseService(dao: WaveAccessDao): DBService{
    return DBService(dao)
}
}