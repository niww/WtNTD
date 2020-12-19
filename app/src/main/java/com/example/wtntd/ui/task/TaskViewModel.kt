package com.example.wtntd.ui.task

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.wtntd.data.Repository
import com.example.wtntd.data.Task
import com.example.wtntd.model.TaskResult
import com.example.wtntd.ui.base.BaseViewModel
import com.example.wtntd.ui.main.MainViewState

class TaskViewModel(private val repository:Repository = Repository) :BaseViewModel<Task?, TaskViewState>() {
    private var pendingTask: Task? = null

    fun saveChange(task: Task?){
        pendingTask = task
    }

    override fun onCleared() {
        if (pendingTask!=null){
            repository.saveTask(pendingTask!!)
        }
    }

    fun loadTask(taskId: String){
        repository.getTaskById(taskId).observeForever { object : Observer<TaskResult> {
            override fun onChanged(t: TaskResult?) {
                t ?: return
                when (t) {
                    is TaskResult.Success<*> -> {
                        viewStateLiveData.value = TaskViewState(task = t.data as? Task)
                    }
                    is TaskResult.Error ->
                        viewStateLiveData.value = TaskViewState(er = t.error)
                }
            } }
        }
    }

}