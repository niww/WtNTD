package com.example.wtntd.ui.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wtntd.R
import com.example.wtntd.data.Task
import com.example.wtntd.ui.base.BaseActivity
import com.example.wtntd.ui.adapters.ChildTaskAdapter
import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.android.synthetic.main.parent_recycler_view.*
import java.util.*

class TaskActivity : BaseActivity<Task?, TaskViewState>() {

    companion object {
        private val EXTRA_TASK = TaskActivity::class.java.name + "extra.TASK"
        fun getStartIntent(context: Context, task: Task?): Intent {
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra(EXTRA_TASK, task)
            return intent
        }
    }

    override val layoutRes: Int = R.layout.activity_task
    private var task: Task? = null

    override val viewModel: TaskViewModel by lazy {
        ViewModelProvider(
            this
        ).get(TaskViewModel::class.java)
    }

    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            saveTaskAfterChanged()
        }
    }

    private fun saveTaskAfterChanged() {
        if (task_todo.text == null || task_todo.text!!.length < 3) return
        task = task?.copy(task = task_todo.text.toString()) ?: Task(
            id = UUID.randomUUID().toString(),
            task = task_todo.text.toString(),
            listTask = listOf(task_todo.text.toString())
        )
        task?.let { viewModel.saveChange(it) } // todo task not save
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        task = intent.getParcelableExtra(EXTRA_TASK)

        setSupportActionBar(task_toolbar)
        if (task != null) {
            supportActionBar?.title = task!!.task
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()

    }

    private fun initView() {
        if (task != null) {
            task_todo.setText(task?.task ?: "")
            task_todo.addTextChangedListener(textChangeListener)

            rv_task_activity?.apply {
                layoutManager =
                    LinearLayoutManager(this@TaskActivity, LinearLayoutManager.VERTICAL, false)
                adapter = ChildTaskAdapter(task?.listTask!!)
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

    override fun renderData(date: Task?) {
        this.task = date
        initView()
    }
}