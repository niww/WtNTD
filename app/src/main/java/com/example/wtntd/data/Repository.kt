package com.example.wtntd.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Repository {

    private val listTaskLiveData = MutableLiveData<List<Task>>()
    private val tasks = mutableListOf(
        Task(UUID.randomUUID().toString(),"1", listOf("1111", "1111", "1111")),
        Task(UUID.randomUUID().toString(),"2", listOf("222", "222", "222")),
        Task(UUID.randomUUID().toString(),"3", listOf("333", "333", "333"))
        )

    init {
        listTaskLiveData.value = tasks
    }

    fun getListTasks():LiveData<List<Task>>{
        return listTaskLiveData
    }

    fun saveTask(task: Task){
        addOrReplace(task)
        listTaskLiveData.value = tasks
    }

    private fun addOrReplace(task: Task) {
        for (i in 0 until tasks.size){
            if (tasks[i] == task){
                tasks[i] = task
                return
            }
        }
        tasks.add(task)
    }



}