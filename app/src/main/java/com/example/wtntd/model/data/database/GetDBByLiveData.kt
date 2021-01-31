package com.example.wtntd.model.data.database

import android.widget.EditText
import com.example.wtntd.model.data.room.AppDataBase
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.example.wtntd.ui.App
import timber.log.Timber
import java.util.*
import kotlin.random.Random

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

    override fun loadListToDo(list: MutableList<SubRoomTaskToDo>, uid: Long) {
        dataBase.getSubRoomTask().getByUid(uid).observeForever { l->
            list.clear()
            l.map { list.add(it) }

        }
    }


    override fun saveToDO(editText: EditText, uid: Long) {
        Thread {
            dataBase.getSubRoomTask().insert(
                SubRoomTaskToDo(
                    uid+1000+ Random.nextLong(1000L),// fixme
                    uid,
                    editText.text.toString()
                )
            )

        }.start()
    }

    override fun saveDataToDB(list: MutableList<RoomTaskToDo>, editText: EditText) {

        Thread {
            Timber.d("ListTask thread ${Thread.currentThread().name}")
            Timber.d("ListTask size in thread ${list.size}")

            dataBase.getRoomTask().insert(
                RoomTaskToDo(
                    list.size.toLong()+1,
                    editText.text.toString()
                )
            )

        }.start()

            dataBase.getSubRoomTask().getAll().observeForever {
                Timber.d("ListTask size in thread ${it}")

            }

    }
}