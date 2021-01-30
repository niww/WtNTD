package com.example.wtntd.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.database.GetDBByLiveData
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.example.wtntd.ui.adapters.NoteItemAdapter
import com.example.wtntd.ui.adapters.NoteListItemAdapter
import kotlinx.android.synthetic.main.activity_list_todo.*
import timber.log.Timber

class ListToDo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_todo)

        val toDoId = intent.getLongExtra("ListToDo", 0L)
        val dataBase: IGetDataBase = GetDBByLiveData()
        var list = mutableListOf<SubRoomTaskToDo>()


        setSupportActionBar(findViewById(R.id.app_bar_list))

        supportActionBar?.title = toDoId.toString()

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        dataBase.loadListToDo(list, toDoId)


        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = NoteListItemAdapter(list) {
                Timber.d(" ListTask test - ${it.task}")

            }
        }


        add_new_todo.setOnClickListener {
            dataBase.saveToDO(new_todo, toDoId)
            NoteListItemAdapter(list).notifyDataSetChanged()
        }
    }
}