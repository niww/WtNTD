package com.example.wtntd.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.data.Repository
import com.example.wtntd.data.Task
import com.example.wtntd.ui.task.TaskActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapterToDo: TaskToDoItemAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapterToDo = TaskToDoItemAdapter(object : TaskToDoItemAdapter.OnItemClickListener {
            override fun onItemClick(task: Task) {
                openTaskScreen(task)
            }
        })

        adapterToDo.listTask = Repository.getListTaskToDo()

        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = adapterToDo
        }

        viewModel.viewState().observe(this, Observer<MainViewState> {
            it?.let { adapterToDo.listTask = it.listTask }
        })

        floatingActionButton.setOnClickListener {

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

    private fun openTaskScreen(task: Task?) {
        val intent = TaskActivity.getStartIntent(this, task)
        startActivity(intent)
    }

}




