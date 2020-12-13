package com.example.wtntd.ui.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wtntd.R
import com.example.wtntd.data.Task
import com.example.wtntd.ui.main.ChildTaskToDoItemAdapter
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel

    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            saveTaskAfterChanged()
        }
    }

    private fun saveTaskAfterChanged() {
        if (task_todo.text == null || task_todo.text!!.length <3){
            Handler().postDelayed(object : Runnable {
                override fun run() {

                    task = task?.copy(task = task_todo.text.toString())
                    if (task!= null ) viewModel.saveChange(task)
                }
            }, 2000L)
        }
    }

    companion object {
        private val EXTRA_TASK = TaskActivity::class.java.name + "extra.TASK"
        fun getStartIntent(context: Context, task: Task?): Intent {
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra(EXTRA_TASK, task)
            return intent
        }
    }

    private var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        setSupportActionBar(task_appbar)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)



        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        task = intent.getParcelableExtra(EXTRA_TASK)

        initView()

    }

    private fun initView() {
        if (task!= null){
            task_todo.setText(task?.task ?: "")
            task_todo.addTextChangedListener(textChangeListener)

            rv_task_activity?.apply {
                layoutManager = LinearLayoutManager(this@TaskActivity, LinearLayoutManager.VERTICAL, false)
                adapter = ChildTaskToDoItemAdapter(task!!.listTask)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


}