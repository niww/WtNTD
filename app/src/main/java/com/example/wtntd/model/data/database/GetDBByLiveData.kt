package com.example.wtntd.model.data.database

import android.widget.EditText
import com.example.wtntd.model.data.room.AppDataBase
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.ui.App
import timber.log.Timber
import java.util.*

class GetDBByLiveData : IGetDataBase {


    val dataBase = App().getInstance().getDataBase()
    override fun getDB() = dataBase

    override fun loadDB(list: MutableList<RoomTaskToDo>) {

        dataBase.getRoomTask().getAll().observeForever { roomList ->
            Timber.d("LiveDataTest ${Thread.currentThread().name}")

            list.clear()
            roomList.map { list.add(it) }
            Timber.d("LiveDataTest ${list.toString()}")

        }
    }

    override fun saveDataToDB(list: MutableList<RoomTaskToDo>, editText: EditText) {

        Thread {
            Timber.d("ListTask size in thread ${list.size}")

            dataBase.getRoomTask().insert(
                RoomTaskToDo(
                    list.size.toLong()+1,
                    editText.text.toString()
                )
            )
        }.start()


    }
}