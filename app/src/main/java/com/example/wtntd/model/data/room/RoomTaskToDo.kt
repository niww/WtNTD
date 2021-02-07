package com.example.wtntd.model.data.room

import android.view.autofill.AutofillId
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomTaskToDo(

    val task: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Long =0

    var openToDo: Int = 0
    var closedToDo: Int = 0

}