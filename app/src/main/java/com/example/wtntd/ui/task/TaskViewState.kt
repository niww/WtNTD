package com.example.wtntd.ui.task

import com.example.wtntd.data.Task
import com.example.wtntd.ui.base.BaseViewState

class TaskViewState(val task: Task? = null, val er: Throwable? = null) :
    BaseViewState<Task?>(task, er)