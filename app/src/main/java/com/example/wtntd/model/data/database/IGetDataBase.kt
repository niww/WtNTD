package com.example.wtntd.model.data.database

import android.widget.EditText
import com.example.wtntd.model.data.room.RoomTaskToDo

interface IGetDataBase {
    fun loadDB(list : MutableList<RoomTaskToDo>)
    fun saveDataToDB(list: MutableList<RoomTaskToDo>, editText: EditText)
}