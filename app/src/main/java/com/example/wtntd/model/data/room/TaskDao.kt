package com.example.wtntd.model.data.room

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM roomtasktodo")
    fun getAll():List<RoomTaskToDo>

    @Insert
    fun insert(roomTaskToDo: RoomTaskToDo)

    @Update
    fun update(roomTaskToDo: RoomTaskToDo)

    @Delete
    fun delete(roomTaskToDo: RoomTaskToDo)
}