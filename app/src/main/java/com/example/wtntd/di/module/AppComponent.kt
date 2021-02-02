package com.example.wtntd.di.module

import com.example.wtntd.model.data.database.GetDBByRXJava
import com.example.wtntd.ui.activity.ListToDo
import com.example.wtntd.ui.activity.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(listToDo: ListToDo)
    fun inject(getDBByRXJava: GetDBByRXJava)

}
