package com.example.wtntd.model.data.room

import androidx.room.*

@Dao
interface SubTaskDao {
    @Query("SELECT * FROM subroomtasktodo")
    fun getAll():List<SubRoomTaskToDo>

    @Insert
    fun insert(subRoomTaskToDo: SubRoomTaskToDo)

    @Update
    fun update(subRoomTaskToDo: SubRoomTaskToDo)

    @Delete
    fun delete(subRoomTaskToDo: SubRoomTaskToDo)
}