package com.example.wtntd.data

object Repository {

    private val list: List<TaskToDo>

    init {
        list = listOf(
            TaskToDo("1", listOf("1111", "1111", "1111")),
            TaskToDo("2", listOf("222", "222", "222")),
            TaskToDo("3", listOf("333", "333", "333")),
            TaskToDo("4", listOf("444", "444", "444")),
            TaskToDo("5", listOf("555", "555", "555")),
            TaskToDo("5", listOf("555", "555", "555")),
            TaskToDo("5", listOf("555", "555", "555")),
            TaskToDo("5", listOf("555", "555", "555"))

        )
    }

    fun getListTaskToDo():List<TaskToDo>{
        return list
    }
}