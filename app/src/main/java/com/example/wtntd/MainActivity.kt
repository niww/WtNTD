package com.example.wtntd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whntd.data.NoteItemAdapter
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val TAG = "TAGG"
        val database = Firebase.database
        val myDB = database.getReference("ListNotes")
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        val list = mutableListOf("12", "sdsd", "Work", "Yes")
        myDB.setValue(list)


        recyclerView.layoutManager = linearLayoutManager
        adapter = NoteItemAdapter(list)
        recyclerView.adapter = adapter
    }

}
