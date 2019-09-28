package com.zaripov.waveaccesstestwork.di.modules

import com.zaripov.waveaccesstestwork.api.ApiService
import com.zaripov.waveaccesstestwork.db.DBService
import com.zaripov.waveaccesstestwork.general.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiServiceModule::class, DatabaseServiceModule::class])
class RepositoryModule{
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, dbService: DBService): Repository {
        return Repository(apiService, dbService)
    }
}