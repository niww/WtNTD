package com.example.wtntd.ui.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.wtntd.R
import com.example.wtntd.data.Task

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