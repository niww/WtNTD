package com.example.wtntd.ui

import android.app.Application
import com.example.wtntd.di.module.AppComponent
import com.example.wtntd.di.module.AppModule
import com.example.wtntd.di.module.DaggerAppComponent
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