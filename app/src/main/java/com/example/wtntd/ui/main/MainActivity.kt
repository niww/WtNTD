package com.example.wtntd.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.TooltipCompat
import androidx.lifecycle.Observer
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

    companion object {
        fun getStartIntent(context: Context) = Intent(context,MainActivity::class.java)
    }

    private lateinit var adapterToDo: TaskAdapter
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override val layoutRes: Int= R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.tool_bar))


        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.viewState().observe(this, Observer {
            it?.let { adapterToDo.listTask = it.listTask!! }
        })

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

        tool_bar.setNavigationOnClickListener {
            //todo
            Toast.makeText(this, "Navigation", Toast.LENGTH_SHORT).show()
        }

        tool_bar.setOnMenuItemClickListener {
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



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar,menu)
        return true
    }

}




