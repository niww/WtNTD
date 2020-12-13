package com.example.wtntd.ui.task

import androidx.lifecycle.ViewModel
import com.example.wtntd.data.Repository
import com.example.wtntd.data.Task

class TaskViewModel(private val repository:Repository = Repository) :ViewModel() {
    private var pendingTask: Task? = null

    fun saveChange(task: Task?){
        pendingTask = task
    }

    override fun onCleared() {
        if (pendingTask!=null){
            repository.saveTask(pendingTask!!)
        }
    }

}