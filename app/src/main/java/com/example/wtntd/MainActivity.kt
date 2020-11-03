package com.example.wtntd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whntd.data.NoteItemAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, true)

        recyclerView.layoutManager = linearLayoutManager
        adapter = NoteItemAdapter()
        recyclerView.adapter = adapter
    }

}
