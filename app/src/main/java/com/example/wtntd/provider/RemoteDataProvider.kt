package com.example.wtntd.provider

import androidx.lifecycle.LiveData
import com.example.wtntd.data.Task
import com.example.wtntd.model.TaskResult

interface RemoteDataProvider {
    fun subscriptToAllTasks() : LiveData<TaskResult>
    fun getTaskById(id : String) : LiveData<TaskResult>
    fun saveTask(task: Task) : LiveData<TaskResult>
}