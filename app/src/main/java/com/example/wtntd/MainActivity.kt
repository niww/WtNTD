package com.example.wtntd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.data.NoteItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteItemAdapter
    private lateinit var database: DatabaseReference
    private val auth = FirebaseAuth.getInstance()
    private val list = mutableListOf("12", "sdsd", "Work", "Yes", "Test", "test")
    private val user = auth.currentUser?.displayName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "TAGG"
        val USER = "userss"

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)


        database = Firebase.database.reference
        auth.currentUser.let { it?.uid }
            ?.let {
                database.child("users").child(it).child(user.toString()).child("NotesList")
                    .setValue(list)
            }

        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)


        recyclerView.layoutManager = linearLayoutManager
        adapter = NoteItemAdapter(list)
        recyclerView.adapter = adapter
        button.setOnClickListener {
            list.add("Work")
            (recyclerView.adapter as NoteItemAdapter).notifyDataSetChanged()

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

