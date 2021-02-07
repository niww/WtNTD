package com.example.wtntd.ui.activity

import androidx.lifecycle.ViewModel
import com.example.wtntd.model.data.database.GetDataBase
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.model.data.room.SubRoomTaskToDo

class MainViewModel: ViewModel() {

    var listTask = mutableListOf<RoomTaskToDo>()
    val dataBase: IGetDataBase = GetDataBase()

    fun getList(): MutableList<RoomTaskToDo> {
        dataBase.loadDB(listTask)
        return listTask
    }

    fun deleteToDo(list: MutableList<SubRoomTaskToDo>){
        dataBase.deleteSubToDo(list)

    }

}