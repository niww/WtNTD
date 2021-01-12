package com.example.wtntd.model.data.firestore

import com.example.wtntd.model.data.TaskToDo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.core.UserData
import com.google.firebase.firestore.ktx.getField
import timber.log.Timber

class FireStore {

    companion object {
        fun instance() = FireStore()
    }

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val user = auth.currentUser?.displayName

    fun getUserName() = user

    fun getData(): MutableList<String> {
        val data = mutableListOf<String>()

        db.collection("users").addSnapshotListener { value, error ->
            value?.let {
                data.clear()
                it.documents.map {
                    it.get("task")
                    data.add(it.get("task").toString())
                    Timber.d("${it.get("task")}")
                    Timber.d("${data.toString()}")
                }
            }
            error?.let {
                it.message
            }
        }

        return data
    }

    fun saveData(todo: String) {

        db.collection("users").add(TaskToDo(todo, listOf(todo)))
    }

    fun addSubToDo(id: Int) {
    }

}