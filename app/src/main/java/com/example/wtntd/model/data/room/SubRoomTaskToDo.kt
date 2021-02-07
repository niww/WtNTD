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
        childColumns = ["uid_sub"],
        onDelete = CASCADE
    )]
)
data class SubRoomTaskToDo(

    @ColumnInfo(name = "uid_sub")
    val uidSub: Long,
    @ColumnInfo(name = "SubTasks")
    val task: String
){
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0


    var isChecked = false

}