package com.example.wtntd.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(val id: String = "", val task: String = "", val listTask: List<String> = listOf("")) :
    Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as Task
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}