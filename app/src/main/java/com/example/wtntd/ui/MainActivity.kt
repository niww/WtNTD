package com.example.wtntd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.ui.adapters.NoteItemAdapter
import com.example.wtntd.model.data.database.GetDataBase
import com.example.wtntd.model.data.database.IGetDataBase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    //    val database = FireStore.instance()
    var listTask = mutableListOf<RoomTaskToDo>()

    val dataBase:IGetDataBase = GetDataBase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        setSupportActionBar(findViewById(R.id.app_bar))

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)



        dataBase.loadDB(listTask)


        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = NoteItemAdapter(listTask)
        }
        NoteItemAdapter(listTask).notifyDataSetChanged()


        val swipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) {


                    recyclerView.adapter?.notifyDataSetChanged()

                    Timber.d(" Swipe adapterPosition ${viewHolder.adapterPosition}")
                    Timber.d(" Swipe layoutPosition${viewHolder.layoutPosition}")
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(recyclerView)

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
//
               dataBase.saveDataToDB(listTask,editText)
            }
            .show()
    }


}




