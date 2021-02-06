package com.example.wtntd.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.database.GetDBByLiveData
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.ui.adapters.NoteItemAdapter
import com.example.wtntd.model.data.database.IGetDataBase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val dataBase: IGetDataBase = GetDBByLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val listTask = mainViewModel.getList()

        setSupportActionBar(findViewById(R.id.app_bar))

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)


        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = NoteItemAdapter(listTask) {
                Timber.d(" ListTask test - ${it.task}")
                Timber.d(" ListTask test - ${it.uid}")
//                dataBase.getDB().getRoomTask().delete(it)

                val intent = Intent(this@MainActivity, ListToDo::class.java)
                intent.putExtra("ListToDo", it.uid)
                intent.putExtra("NameListToDo", it.task)
                context.startActivity(intent)
            }
        }

        floatingActionButton.setOnClickListener { view ->
            createNewToDo()
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }


    fun createNewToDo() {
        val editText = EditText(this)
        MaterialAlertDialogBuilder(this)
            .setTitle("New ToDo")
            .setView(editText)
            .setNegativeButton("Cancel", { dialogInterface, i -> "Ok" })
            .setPositiveButton(
                "Ok"
            ) { dialogInterface, i ->
                dataBase.saveDataToDB(editText)
            }
            .show()
    }


}




