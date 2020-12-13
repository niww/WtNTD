package com.example.wtntd.data

import java.util.*

object Repository {

    private val listTaskLiveData: List<Task>

    init {
        listTaskLiveData = listOf(
            Task(UUID.randomUUID().toString(),"1", listOf("1111", "1111", "1111")),
            Task(UUID.randomUUID().toString(),"2", listOf("222", "222", "222")),
            Task(UUID.randomUUID().toString(),"3", listOf("333", "333", "333")),
            Task(UUID.randomUUID().toString(),"4", listOf("444", "444", "444")),
            Task(UUID.randomUUID().toString(),"5", listOf("555", "555", "555")),
            Task(UUID.randomUUID().toString(),"5", listOf("555", "555", "555")),
            Task(UUID.randomUUID().toString(),"5", listOf("555", "555", "555")),
            Task(id = UUID.randomUUID().toString(), task = "5",listTask =  listOf("555", "555", "555"))
        )
    }

    fun getListTaskToDo():List<Task>{
        return listTaskLiveData
    }


}