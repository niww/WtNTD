package com.example.wtntd.model.data.database

import android.widget.EditText
import com.example.wtntd.model.data.room.AppDataBase
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.model.data.room.SubRoomTaskToDo

interface IGetDataBase {
    fun getDB():AppDataBase
    fun loadDB(list : MutableList<RoomTaskToDo>)
    fun loadListToDo(list : MutableList<SubRoomTaskToDo>, uid: Long)
    fun saveDataToDB(editText: EditText)
    fun saveToDO(editText: EditText, uid: Long)
}