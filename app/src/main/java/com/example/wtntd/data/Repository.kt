package com.example.wtntd.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wtntd.provider.FireStoreProvider
import java.util.*

object Repository {

    private val remoteDataProvider = FireStoreProvider()

    fun getTasks() = remoteDataProvider.subscriptToAllTasks()
    fun saveTask(task: Task) = remoteDataProvider.saveTask(task)
    fun getTaskById(id: String) = remoteDataProvider.getTaskById(id)


}