package com.example.wtntd.model.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubTaskDao {
    @Query("SELECT * FROM subroomtasktodo")
    fun getAll(): LiveData<List<SubRoomTaskToDo>>

    @Query("SELECT * FROM subroomtasktodo WHERE uid_sub=:uid")
    fun getByUid(uid:Long): LiveData<List<SubRoomTaskToDo>>

    @Insert
    fun insert(subRoomTaskToDo: SubRoomTaskToDo)

    @Update
    fun update(subRoomTaskToDo: SubRoomTaskToDo)

    @Delete
    fun delete(subRoomTaskToDo: SubRoomTaskToDo)
}