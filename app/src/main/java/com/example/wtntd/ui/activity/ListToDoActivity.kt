package com.example.wtntd.ui.activity

import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.database.GetDataBase
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.example.wtntd.ui.adapters.NoteListItemAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_list_todo.*

class ListToDoActivity : AppCompatActivity() {
    val dataBase: IGetDataBase = GetDataBase()
    var nameListToDo: String = ""
    var idListToDo: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_todo)

        var list = mutableListOf<SubRoomTaskToDo>()
        nameListToDo = intent.getStringExtra("NameListToDo")
        idListToDo = intent.getLongExtra("ListToDo", 0L)


        setSupportActionBar(findViewById(R.id.app_bar_list))
        supportActionBar?.title = "$nameListToDo $idListToDo"

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)


        dataBase.loadListToDo(list, idListToDo)


        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = NoteListItemAdapter(list, this@ListToDoActivity) { dataBase.updateToDo(it) }
        }


        add_new_todo.setOnClickListener {
            dataBase.saveToDO(new_todo.text.toString(), idListToDo)
            new_todo.text.clear()
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_todo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete_list -> {
                dataBase.deleteToDo(idListToDo)
                this.onBackPressed()
            }
            R.id.action_rename_list -> {
                renameListToDo(idListToDo, nameListToDo)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun renameListToDo(id: Long, nameListToDo: String) {
        val editText = EditText(this)
        editText.text = Editable.Factory().newEditable(nameListToDo)
        MaterialAlertDialogBuilder(this)
            .setTitle("Rename ToDo")
            .setView(editText)
            .setNegativeButton("Cancel", { dialogInterface, i -> "Ok" })
            .setPositiveButton(
                "Ok"
            ) { dialogInterface, i ->
                dataBase.renameToDo(id, nameListToDo)
            }
            .show()
    }

}