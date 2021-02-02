package com.example.wtntd.ui.activity

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.database.GetDBByLiveData
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.example.wtntd.ui.App
import com.example.wtntd.ui.adapters.NoteListItemAdapter
import kotlinx.android.synthetic.main.activity_list_todo.*
import javax.inject.Inject

class ListToDo : AppCompatActivity() {
        @Inject lateinit var getDBByLiveData: IGetDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_todo)

        val toDoId = intent.getLongExtra("ListToDo", 0L)
        val nameListToDo = intent.getStringExtra("NameListToDo")

        App.component.inject(this)

        val dataBase: IGetDataBase = getDBByLiveData

        var list = mutableListOf<SubRoomTaskToDo>()
        val editText = findViewById<EditText>(R.id.new_todo)

        setSupportActionBar(findViewById(R.id.app_bar_list))

        supportActionBar?.title = "$nameListToDo $toDoId"

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        dataBase.loadListToDo(list, toDoId)


        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = NoteListItemAdapter(list, this@ListToDo)
        }


        add_new_todo.setOnClickListener {
            dataBase.saveToDo(editText, toDoId)
            NoteListItemAdapter(list, this).notifyDataSetChanged()

        }
    }
}