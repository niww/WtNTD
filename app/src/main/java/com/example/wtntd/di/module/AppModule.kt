package com.example.wtntd.di.module

import com.example.wtntd.ui.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}