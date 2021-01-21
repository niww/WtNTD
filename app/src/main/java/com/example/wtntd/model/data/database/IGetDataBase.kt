package com.example.wtntd.model.data.database

import android.widget.EditText
import com.example.wtntd.model.data.room.AppDataBase
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.model.data.room.SubRoomTaskToDo

interface IGetDataBase {
    fun getDB():AppDataBase
    fun loadDB(list : MutableList<RoomTaskToDo>)
    fun saveDataToDB(list: MutableList<RoomTaskToDo>,editText: EditText)
    fun getSubListTask(roomTaskToDo: RoomTaskToDo, list: MutableList<SubRoomTaskToDo>)
    fun insertSubToDo(subRoomTaskToDo: SubRoomTaskToDo)
}