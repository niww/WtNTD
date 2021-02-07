package com.example.wtntd.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.database.GetDataBase
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.example.wtntd.ui.adapters.NoteListItemAdapter
import kotlinx.android.synthetic.main.activity_list_todo.*

class ListToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_todo)

        val toDoId = intent.getLongExtra("ListToDo", 0L)
        val nameListToDo = intent.getStringExtra("NameListToDo")
        val dataBase: IGetDataBase = GetDataBase()
        var list = mutableListOf<SubRoomTaskToDo>()


        setSupportActionBar(findViewById(R.id.app_bar_list))

        supportActionBar?.title = "$nameListToDo $toDoId"

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)


        dataBase.loadListToDo(list, toDoId)


        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = NoteListItemAdapter(list, this@ListToDoActivity) { dataBase.updateToDo(it) }
        }


        add_new_todo.setOnClickListener {
            dataBase.saveToDO(new_todo.text.toString(), toDoId)
            new_todo.text.clear()
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}