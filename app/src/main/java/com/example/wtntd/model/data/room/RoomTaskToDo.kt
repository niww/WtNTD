package com.example.wtntd.model.data.room

import android.view.autofill.AutofillId
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomTaskToDo(
    @PrimaryKey
    val uid :Long ,
    val task: String
    )