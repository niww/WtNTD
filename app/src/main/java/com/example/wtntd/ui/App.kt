package com.example.wtntd.ui

import android.app.Application
import androidx.room.Room
import com.example.wtntd.di.module.AppComponent
import com.example.wtntd.di.module.AppModule
import com.example.wtntd.di.module.DaggerAppComponent
import com.example.wtntd.model.data.room.AppDataBase
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        component = DaggerAppComponent
            .builder().appModule(AppModule(this)).build()
    }

    fun getInstance() = instance

}