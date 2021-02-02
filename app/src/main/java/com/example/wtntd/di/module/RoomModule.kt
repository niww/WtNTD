package com.example.wtntd.di.module

import androidx.room.Room
import com.example.wtntd.model.data.database.GetDBByLiveData
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.model.data.room.AppDataBase
import com.example.wtntd.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    fun getRoom(app: App):AppDataBase{
        return Room.databaseBuilder(app, AppDataBase::class.java, "database").build()
    }

}