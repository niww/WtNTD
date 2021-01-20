package com.example.wtntd.model.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.common.util.concurrent.ListenableFuture

@Dao
interface TaskDao {
    @Query("SELECT * FROM roomtasktodo")
    fun getAll(): LiveData<List<RoomTaskToDo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomTaskToDo: RoomTaskToDo)

    @Update
    fun update(roomTaskToDo: RoomTaskToDo)

    @Delete
    fun delete(roomTaskToDo: RoomTaskToDo)
}