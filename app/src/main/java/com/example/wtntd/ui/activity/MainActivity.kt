package com.example.wtntd.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.ui.adapters.NoteItemAdapter
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.ui.App
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var listTask = mutableListOf<RoomTaskToDo>()
//    val dataBase:IGetDataBase = GetDBByLiveData()

    @Inject lateinit var getDBByLiveData: IGetDataBase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.component.inject(this)

        setSupportActionBar(findViewById(R.id.app_bar))

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

//        dataBase.loadDB(listTask)
        getDBByLiveData.loadDB(listTask)


        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = NoteItemAdapter(listTask) {
                Timber.d(" ListTask test - ${it.task}")
                Timber.d(" ListTask test - ${it.uid}")
//                dataBase.getDB().getRoomTask().delete(it)

               val intent =  Intent(this@MainActivity, ListToDo::class.java)
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
//                dataBase.saveDataToDB(listTask,editText)
                getDBByLiveData.saveDataToDB(listTask,editText)
            }
            .show()
    }


}




