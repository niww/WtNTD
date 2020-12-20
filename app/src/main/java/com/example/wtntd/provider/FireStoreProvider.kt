package com.example.wtntd.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wtntd.data.Task
import com.example.wtntd.error.NoAuthException
import com.example.wtntd.model.TaskResult
import com.example.wtntd.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import java.lang.Exception

class FireStoreProvider : RemoteDataProvider {

    //    private val TAG = "${FireStoreProvider::class.java.simpleName} :"
    private val TAG = "SaveTask"
    private val USER_COLLECTION = "users"
    private val TASK_COLLECTION = "tasks"
    private val db = FirebaseFirestore.getInstance()

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val userTaskCollection: CollectionReference
        get() = currentUser?.let {
            db.collection(USER_COLLECTION).document(it.uid).collection(TASK_COLLECTION)
        } ?: throw NoAuthException()


    override fun subscriptToAllTasks(): LiveData<TaskResult> = MutableLiveData<TaskResult>().apply {

        try {
            userTaskCollection.addSnapshotListener { snapshot, error ->
                value = error?.let {
                    TaskResult.Error(error)
                } ?: snapshot?.let {
                    val tasks = it.documents.map { it -> it.toObject(Task::class.java) }
                    TaskResult.Success(tasks)
                }
            }
        } catch (e: Throwable) {
            value = TaskResult.Error(e)
        }
    }

    override fun getTaskById(id: String): LiveData<TaskResult> =
        MutableLiveData<TaskResult>().apply {
            try {
                userTaskCollection.document(id).get()
                    .addOnSuccessListener {
                        value = TaskResult.Success(it.toObject(Task::class.java))
                    }
                    .addOnFailureListener { value = TaskResult.Error(it) }

            } catch (e: Throwable) {
                value = TaskResult.Error(e)
            }
        }

    override fun saveTask(task: Task): LiveData<TaskResult> = MutableLiveData<TaskResult>().apply {
        try {
            userTaskCollection.document(task.id).set(task).addOnCompleteListener {
                OnSuccessListener<Void> {
                    Log.d(TAG, "Task $task is save")
                    value = TaskResult.Success(task)
                }
                OnFailureListener { p0 ->
                    Log.d(TAG, "Task $task is save")
                    value = TaskResult.Error(p0)
                }
            }
        } catch (e: Throwable) {
            value = TaskResult.Error(e)
        }
    }

    override fun getCurrentUser(): LiveData<User> = MutableLiveData<User>().apply {
        value = currentUser?.let { User(it.displayName ?: "", it.email ?: "") }
    }

}