package com.example.wtntd.di.module

import com.example.wtntd.model.data.database.GetDBByLiveData
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}