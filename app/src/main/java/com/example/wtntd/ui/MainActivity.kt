package com.example.wtntd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.firestore.FireStore
import com.example.wtntd.ui.adapters.NoteItemAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()

    private val user = auth.currentUser?.displayName
    val db = FireStore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        setSupportActionBar(findViewById(R.id.app_bar))
        supportActionBar?.let {
            title = user
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

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

                }
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipe)

        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = NoteItemAdapter(db.getData(), this@MainActivity)
        }
        itemTouchHelper.attachToRecyclerView(recyclerView)

        floatingActionButton.setOnClickListener {

            val editText = EditText(this)
            MaterialAlertDialogBuilder(this)
                .setTitle("New ToDo")
                .setView(editText)
                .setNegativeButton("Cancel", { dialogInterface, i -> "Ok" })
                .setPositiveButton(
                    "Ok"
                ) { dialogInterface, i -> db.saveData(editText.text.toString()) }
                .show()

            recyclerView.adapter?.notifyDataSetChanged()

        }




    }


    override fun onPause() {
        super.onPause()

        auth.currentUser.let { it?.uid }
            ?.let {

            }

    }

}




