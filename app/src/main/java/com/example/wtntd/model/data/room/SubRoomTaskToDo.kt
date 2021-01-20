package com.example.wtntd.model.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomTaskToDo::class,
        parentColumns = ["uid"],
        childColumns = ["uid_sub"]
    )]
)
data class SubRoomTaskToDo(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "uid_sub")
    val uidSub: Long,
    @ColumnInfo(name = "SubTasks")
    val task: String
//    val listTask: MutableList<String>
)