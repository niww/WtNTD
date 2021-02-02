package com.example.wtntd.di.module

import com.example.wtntd.model.data.database.GetDBByLiveData
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

}
