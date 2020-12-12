package com.example.wtntd.ui.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wtntd.R
import com.example.wtntd.data.Task
import com.example.wtntd.ui.main.ChildTaskToDoItemAdapter
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

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
        task = intent.getParcelableExtra(EXTRA_TASK)

        initView()

    }

    private fun initView() {
        if (task!= null){
            task_todo.setText(task?.task ?: "")
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