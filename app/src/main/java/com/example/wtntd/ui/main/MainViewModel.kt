package com.example.wtntd.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wtntd.data.Repository

class MainViewModel : ViewModel() {

    private val viewStateLiveData = MutableLiveData<MainViewState>()

    init {
        viewStateLiveData.value = MainViewState(Repository.getListTaskToDo())
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}