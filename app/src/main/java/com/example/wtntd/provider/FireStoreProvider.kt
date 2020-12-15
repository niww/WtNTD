package com.example.wtntd.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wtntd.data.Task
import com.example.wtntd.model.TaskResult
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class FireStoreProvider : RemoteDataProvider {

    private val TAG = "${FireStoreProvider::class.java.simpleName} :"

    private val db = FirebaseFirestore.getInstance()
    private val taskReference = db.collection("Tasks")

    override fun subscriptToAllTasks(): LiveData<TaskResult> {
        TODO("Not yet implemented")
    }

    override fun getTaskById(id: String): LiveData<TaskResult> {
        TODO("Not yet implemented")
    }

    override fun saveTask(task: Task): LiveData<TaskResult> {
        val result = MutableLiveData<TaskResult>()
        taskReference.document(task.id).set(task).addOnCompleteListener {
            object : OnSuccessListener <Void>{
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