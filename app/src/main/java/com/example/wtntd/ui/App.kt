package com.example.wtntd.ui

import android.app.Application
import androidx.room.Room
import com.example.wtntd.model.data.room.AppDataBase
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var db: AppDataBase
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDataBase::class.java, "database").build()
        instance = this
        Timber.plant(Timber.DebugTree())

    }

    fun getInstance() = instance

    fun getDataBase() = db
}