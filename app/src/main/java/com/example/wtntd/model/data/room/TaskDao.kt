package com.example.wtntd.model.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.common.util.concurrent.ListenableFuture
import io.reactivex.Single

@Dao
interface TaskDao {
    @Query("SELECT * FROM roomtasktodo")
    fun getAll(): Single<List<RoomTaskToDo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomTaskToDo: RoomTaskToDo)

    @Update
    fun update(roomTaskToDo: RoomTaskToDo)

    @Delete
    fun delete(roomTaskToDo: RoomTaskToDo)
}