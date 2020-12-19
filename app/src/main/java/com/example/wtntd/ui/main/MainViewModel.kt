package com.example.wtntd.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.wtntd.data.Repository
import com.example.wtntd.data.Task
import com.example.wtntd.model.TaskResult
import com.example.wtntd.ui.base.BaseViewModel
import com.example.wtntd.ui.base.BaseViewState

class MainViewModel(val repository: Repository = Repository) :
    BaseViewModel<List<Task>?, MainViewState>() {

    private val taskObserver = object : Observer<TaskResult> {
        override fun onChanged(t: TaskResult?) {
            t ?: return
            when (t) {
                is TaskResult.Success<*> -> {
                    viewStateLiveData.value = MainViewState(listTask = t.data as? List<Task>)
                }
                is TaskResult.Error ->
                    viewStateLiveData.value = MainViewState(er = t.error)
            }
        }
    }

    private val repositoryNotes = Repository.getTasks()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever { taskObserver }

    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData

    override fun onCleared() {
        repositoryNotes.removeObserver(taskObserver)
        super.onCleared()
    }
}