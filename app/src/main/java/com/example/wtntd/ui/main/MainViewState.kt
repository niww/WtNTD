package com.example.wtntd.ui.main

import com.example.wtntd.data.Task
import com.example.wtntd.ui.base.BaseViewState

class MainViewState(val listTask: List<Task>? = listOf(Task("1", "32432", listOf("1","2","3"))), val er: Throwable? = null) :
    BaseViewState<List<Task>?>(listTask, er)