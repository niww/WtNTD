package com.example.wtntd.model.data.database

import android.widget.EditText
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.example.wtntd.ui.App
import timber.log.Timber

class GetDataBaseByThread:IGetDataBase {

    val dataBase = App().getInstance().getDataBase()

    override fun loadDB(list: MutableList<RoomTaskToDo>) {
        Thread {
            Timber.d("Roomtest ${Thread.currentThread().name}")

            val taskDao = dataBase.getRoomTask()

            Timber.d("Roomtest ${taskDao.getAll()}")

            list.addAll(taskDao.getAll())
            Timber.d("ListTask ${list}")
            Timber.d("ListTask ${dataBase.getSubRoomTask().getAll()}")

        }.start()    }

    override fun saveDataToDB(listTask: MutableList<RoomTaskToDo>, editText: EditText) {
        Thread {
            Timber.d("Roomtest ${Thread.currentThread().name}")

            val taskDao = dataBase.getRoomTask()

            taskDao.insert(
                RoomTaskToDo(
                    (taskDao.getAll().size + 1).toLong(),
                    editText.text.toString()
                )
            )

            listTask.clear()

            listTask.addAll(taskDao.getAll())
            Timber.d("ListTask ${listTask}")

        }.start()
        Thread {
            Thread.sleep(2000)
            Timber.d("Roomtest ${Thread.currentThread().name}")

            val subTaskDao = dataBase.getSubRoomTask()

            subTaskDao.insert(
                SubRoomTaskToDo(
                    (dataBase.getRoomTask().getAll().size ).toLong(),
                    (dataBase.getRoomTask().getAll().size ).toLong(),
                    editText.text.toString()
                )
            )

            Timber.d("ListTask ${subTaskDao.getAll()}")

        }.start()

    }
}