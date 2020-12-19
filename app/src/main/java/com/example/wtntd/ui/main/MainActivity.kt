package com.example.wtntd.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.data.Task
import com.example.wtntd.ui.adapters.TaskAdapter
import com.example.wtntd.ui.base.BaseActivity
import com.example.wtntd.ui.base.BaseViewModel
import com.example.wtntd.ui.task.TaskActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Task>?, MainViewState>() {
    private lateinit var adapterToDo: TaskAdapter
    override val viewModel: BaseViewModel<List<Task>?, MainViewState> by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override val layoutRes: Int= R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        adapterToDo = TaskAdapter(object : TaskAdapter.OnItemClickListener {
            override fun onItemClick(task: Task) {
                openTaskScreen(task)
            }
        })

        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = adapterToDo
        }


        floatingActionButton.setOnClickListener {

            openTaskScreen(null)
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

    override fun renderData(date: List<Task>?) {
        if(date == null) return
        adapterToDo.listTask = date
    }

}




