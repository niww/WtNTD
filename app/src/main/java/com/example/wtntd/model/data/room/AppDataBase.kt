package com.example.wtntd.model.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [RoomTaskToDo::class, SubRoomTaskToDo::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getRoomTask(): TaskDao
    abstract fun getSubRoomTask(): SubTaskDao

}