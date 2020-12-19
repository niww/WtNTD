package com.example.wtntd.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wtntd.data.Task
import com.example.wtntd.model.TaskResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import java.lang.Exception

class FireStoreProvider : RemoteDataProvider {

//    private val TAG = "${FireStoreProvider::class.java.simpleName} :"
    private val TAG = "SaveTask"

    private val db = FirebaseFirestore.getInstance()
    private val taskReference = db.collection("Tasks")

    override fun subscriptToAllTasks() :LiveData<TaskResult> {
        val result = MutableLiveData<TaskResult>()

        taskReference.addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    TaskResult.Error(error)
                } else if (value != null) {
                    val taskResult = mutableListOf<Task>()
                    for (doc: DocumentSnapshot in value) {
                        taskResult.add(doc.toObject(Task::class.java)!!)
                    }
                    result.value = TaskResult.Success(taskResult)
                }
            }
        })
        return result
    }

    override fun getTaskById(id: String): LiveData<TaskResult> {
        val result = MutableLiveData<TaskResult>()

        taskReference.document(id).get()
            .addOnSuccessListener { result.value = TaskResult.Success(Task::class.java) }
            .addOnFailureListener { result.value = TaskResult.Error(it) }

        return result
    }

    override fun saveTask(task: Task): LiveData<TaskResult> {
        val result = MutableLiveData<TaskResult>()
        taskReference.document(task.id).set(task).addOnCompleteListener {
            object : OnSuccessListener<Void> {
                override fun onSuccess(p0: Void?) {
                    Log.d(TAG, "Task $task is save")
                    result.value = TaskResult.Success(task)
                }

            }
            object : OnFailureListener {
                override fun onFailure(p0: Exception) {
                    Log.d(TAG, "Task $task is save")
                    result.value = TaskResult.Error(p0)
                }
            }
        }
        return result
    }
}