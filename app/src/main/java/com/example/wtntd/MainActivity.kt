package com.example.wtntd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whntd.data.NoteItemAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val TAG = "TAGG"
        val db = Firebase.firestore
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        val list = mutableListOf("12", "sdsd", "Work", "Yes")
        val mapNotes = hashMapOf<String, String>(
                "1" to "2",
                "Work" to "Yes",
                "Work" to "Yes",
                "Work" to "Yes",
                "3" to "4"
        )


        db.collection("ListNotes")
                .add(mapNotes)
                .addOnSuccessListener {
                    Log.i(TAG, "DocumentSnapshot added with ID: ${it.id}")
                }
                .addOnFailureListener {
                    Log.w(TAG, "Error adding document")
                }


        recyclerView.layoutManager = linearLayoutManager
        adapter = NoteItemAdapter(list)
        recyclerView.adapter = adapter
    }

}
