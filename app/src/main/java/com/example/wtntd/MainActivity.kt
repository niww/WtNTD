package com.example.wtntd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whntd.data.NoteItemAdapter
import com.example.wtntd.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteItemAdapter
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "TAGG"
        val USER = "userss"

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        val list = mutableListOf("12", "sdsd", "Work", "Yes")

        database = Firebase.database.reference


        recyclerView.layoutManager = linearLayoutManager
        adapter = NoteItemAdapter(list)
        recyclerView.adapter = adapter
    }
}
private fun addNewUser(usedID:String, name:String, email:String?, databaseReference: DatabaseReference){
    val user = User(name,email)
    databaseReference.child("users").setValue(user)

}
