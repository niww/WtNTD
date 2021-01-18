package com.example.wtntd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.firestore.FireStore
import com.example.wtntd.ui.adapters.NoteItemAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {

    val database = FireStore.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        setSupportActionBar(findViewById(R.id.app_bar))
        supportActionBar?.let {
            title = database.getUserName()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        val adapterNote = NoteItemAdapter(database.getData(), this@MainActivity)

        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = adapterNote
        }
        adapterNote.notifyDataSetChanged()


        val swipe = object : ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT){

                    createSubToDo(viewHolder.adapterPosition)
                    recyclerView.adapter?.notifyDataSetChanged()

                    Timber.d(" Swipe adapterPosition ${viewHolder.adapterPosition  }")
                    Timber.d(" Swipe layoutPosition${viewHolder.layoutPosition }")
                    Timber.d("  database.getData().size${database.getData().size }")
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        floatingActionButton.setOnClickListener {view->
            createNewToDo()
            Timber.d(" Swipe layoutPosition${database.getData().size}")
            recyclerView.adapter?.notifyDataSetChanged()

        }

    }


    fun createNewToDo(){
        val editText = EditText(this)
        MaterialAlertDialogBuilder(this)
            .setTitle("New ToDo")
            .setView(editText)
            .setNegativeButton("Cancel", { dialogInterface, i -> "Ok" })
            .setPositiveButton(
                "Ok"
            ) { dialogInterface, i -> database.saveData(editText.text.toString(), database.getData().size +1) }
            .show()
    }

    fun createSubToDo(id:Int){
        val editText = EditText(this)
        MaterialAlertDialogBuilder(this)
            .setTitle("New SudToDo")
            .setView(editText)
            .setNegativeButton("Cancel", { dialogInterface, i -> "Ok" })
            .setPositiveButton(
                "Ok"
            ) { dialogInterface, i -> database.changeTaskList(editText.text.toString(), id) }
            .show()
    }

}




