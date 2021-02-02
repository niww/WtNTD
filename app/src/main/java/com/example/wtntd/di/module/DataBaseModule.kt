package com.example.wtntd.di.module

import com.example.wtntd.model.data.database.GetDBByLiveData
import com.example.wtntd.model.data.database.IGetDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    fun getDB(): IGetDataBase{
        return GetDBByLiveData()
    }

}