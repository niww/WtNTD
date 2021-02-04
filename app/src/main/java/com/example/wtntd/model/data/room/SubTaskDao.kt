package com.example.wtntd.model.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface SubTaskDao {
    @Query("SELECT * FROM subroomtasktodo")
    fun getAll(): Observable<List<SubRoomTaskToDo>>

    @Query("SELECT * FROM subroomtasktodo WHERE uid_sub=:uid")
    fun getByUid(uid:Long): Observable<List<SubRoomTaskToDo>>

    @Insert
    fun insert(subRoomTaskToDo: SubRoomTaskToDo)

    @Update
    fun update(subRoomTaskToDo: SubRoomTaskToDo)

    @Delete
    fun delete(subRoomTaskToDo: SubRoomTaskToDo)
}