package com.example.wtntd.model.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomTaskToDo(
    @PrimaryKey val uid :String,
    val task: String
//    val listTask: MutableList<String>
    )