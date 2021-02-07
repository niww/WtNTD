package com.example.wtntd.model.data.database

import android.widget.EditText
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.example.wtntd.ui.App
import timber.log.Timber
import kotlin.random.Random

class GetDataBase : IGetDataBase {


    val dataBase = App().getInstance().getDataBase()
    override fun getDB() = dataBase

    override fun loadDB(list: MutableList<RoomTaskToDo>) {

        dataBase.getRoomTask().getAll().observeForever { roomList ->
            list.clear()
            list.addAll(roomList)
            Timber.d("LiveDataTest ${list.toString()}")
        }
        list.map {
            it.openToDo = 5
            it.closedToDo = checkClosedToDo(it) //fixme use mediatorLiveData
            Timber.d("LiveCheckClosedToDo ${checkClosedToDo(it).toString()}")
        }
    }

    private fun checkClosedToDo(roomTaskToDo: RoomTaskToDo): Int {
        var counter = 0
        dataBase.getSubRoomTask().getByUid(roomTaskToDo.uid).observeForever {

            it.forEach { subRoomTaskToDo ->
                if (subRoomTaskToDo.isChecked) {
                    counter++
                    Timber.d("TimberCounter ${subRoomTaskToDo.isChecked}")
                    Timber.d("TimberCounter ${counter}")
                }
            }
        }

        return counter
    }

    override fun loadListToDo(list: MutableList<SubRoomTaskToDo>, uid: Long) {
        dataBase.getSubRoomTask().getByUid(uid).observeForever { l ->
            list.clear()
            l.map { list.add(it) }

        }
    }


    override fun saveToDO(string: String, uid: Long) {
        Thread {
            dataBase.getSubRoomTask().insert(
                SubRoomTaskToDo(
                    uid + 1000 + Random.nextLong(1000L),// fixme
                    uid,
                    string
                )
            )

        }.start()
    }

    override fun updateToDo(subRoomTaskToDo: SubRoomTaskToDo) {
        val newSubRoomTaskToDo = subRoomTaskToDo

        when (newSubRoomTaskToDo.isChecked) {
            true -> newSubRoomTaskToDo.isChecked = false
            false -> newSubRoomTaskToDo.isChecked = true
        }

        Thread {
            dataBase.getSubRoomTask().update(
                newSubRoomTaskToDo
            )

        }.start()
    }


    override fun saveDataToDB(editText: EditText) {

        Thread {
            Timber.d("ListTask thread ${Thread.currentThread().name}")

            dataBase.getRoomTask().insert(
                RoomTaskToDo(
                    editText.text.toString()
                )
            )
        }.start()

    }
}