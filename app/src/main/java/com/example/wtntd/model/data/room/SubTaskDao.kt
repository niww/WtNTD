package com.example.wtntd.model.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Single

@Dao
interface SubTaskDao {
    @Query("SELECT * FROM subroomtasktodo")
    fun getAll(): Single<List<SubRoomTaskToDo>>

    @Query("SELECT * FROM subroomtasktodo WHERE uid_sub=:uid")
    fun getByUid(uid:Long): Single<List<SubRoomTaskToDo>>

    @Insert
    fun insert(subRoomTaskToDo: SubRoomTaskToDo)

    @Update
    fun update(subRoomTaskToDo: SubRoomTaskToDo)

    @Delete
    fun delete(subRoomTaskToDo: SubRoomTaskToDo)
}