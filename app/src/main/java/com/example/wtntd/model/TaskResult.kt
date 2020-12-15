package com.example.wtntd.model

sealed class TaskResult {
    data class Success<out T>(val data:T) :TaskResult()
    data class Error(val error:Throwable) :TaskResult()
}