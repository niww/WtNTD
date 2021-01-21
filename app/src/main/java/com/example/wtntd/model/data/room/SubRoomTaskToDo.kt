package com.example.wtntd.model.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomTaskToDo::class,
        parentColumns = ["id"],
        childColumns = ["id_sub"]
    )]
) class SubRoomTaskToDo(

    @ColumnInfo(name = "id_sub")
    val idSub: Long,
    @ColumnInfo(name = "SubTasks")
    val task: String
//    val listTask: MutableList<String>
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}