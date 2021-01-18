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
    private val data = mutableListOf<String>()


    fun getUserName() = user


    fun getData(): MutableList<String> {

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

    fun saveData(name: String, id: Int) {

        db.collection("users").document("$id").set(TaskToDo(name, mutableListOf(name)))


//        db.collection("users").document(name).set(listOf(name))
    }

    fun changeTaskList(text: String, id: Int) {
        val path = db.collection("users").document("$id")

        db.runTransaction { transaction ->
            val snapshot = transaction.get(path)
//            val list = snapshot.getDocumentReference("listTask")
//            val list:MutableList<String>? = snapshot.getField("listTask")
//            val list = snapshot.getField<String>("listTask")

            transaction.update(path, "listTask", text)

        }.addOnSuccessListener {
            Timber.d(" Swipe changeTaskList success")

        }.addOnFailureListener {
            Timber.d(" Swipe changeTaskList $it")

        }
    }


}