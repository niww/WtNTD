package com.example.wtntd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.data.Repository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteItemAdapter
    private lateinit var database: DatabaseReference
    private val auth = FirebaseAuth.getInstance()
    private val list = mutableListOf("")

    private val user = auth.currentUser?.displayName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(findViewById(R.id.toolbar))
        val TAG = "TAGG"
        val USER = "userss"

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.toString())

                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val post = snapshot.value
                val u = snapshot.child("users").child(auth.currentUser?.uid.toString())
                    .child(user.toString()).child("NotesList")
                list.clear()
//                Log.d(USER,u.getValue())
//                Log.d(USER,u.)
                if (u.value != null) {
                    list.addAll(u.value as Collection<String>)
                }

                Log.d(TAG, list.toString())
                Log.d(TAG, post.toString())
                Log.d(TAG, u.value.toString())
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }


        database = Firebase.database.reference

        database.addValueEventListener(postListener)

        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = NoteItemAdapter(Repository.list, this@MainActivity)
        }

        floatingActionButton.setOnClickListener {

            val editText = EditText(this)
            MaterialAlertDialogBuilder(this)
                .setTitle("New ToDo")
                .setView(editText)
                .setNegativeButton("Cancel", { dialogInterface, i -> "Ok" })
                .setPositiveButton(
                    "Ok"
                ) { dialogInterface, i -> list.add(editText.text.toString()) }
                .show()

            recyclerView.adapter?.notifyDataSetChanged()

        }
        bottom_app_bar.setNavigationOnClickListener {
            //todo
            Toast.makeText(this, "Navigation", Toast.LENGTH_SHORT).show()

        }
        bottom_app_bar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_more -> {
                    Toast.makeText(this, "One", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_more1 -> {
                    Toast.makeText(this, "Two", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_more2 -> {
                    Toast.makeText(this, "Three", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }


    }


    override fun onPause() {
        super.onPause()

        auth.currentUser.let { it?.uid }
            ?.let {
                database.child("users").child(it).child(user.toString()).child("NotesList")
                    .setValue(list)
            }

    }

}




