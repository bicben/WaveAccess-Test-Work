package com.zaripov.waveaccesstestwork.di.modules

import com.zaripov.waveaccesstestwork.api.ApiService
import com.zaripov.waveaccesstestwork.general.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiServiceModule::class])
class RepositoryModule{
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }
}