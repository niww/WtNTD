package com.example.wtntd.ui.main

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.data.Task

class TaskAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TaskAdapter.NoteItemViewHolder>() {

    var listTask: List<Task> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface OnItemClickListener {
        fun onItemClick(task: Task)
    }

    val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {

        return NoteItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.parent_recycler_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listTask.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = listTask[position].task
        holder.itemView.setOnClickListener { onItemClickListener.onItemClick(listTask[position]) }

        val childLayoutManager =
            LinearLayoutManager(holder.childrv.context, LinearLayoutManager.VERTICAL, false)

        val swipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.d("Swipe", "swipe")
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipe)

        holder.childrv.apply {
            layoutManager = childLayoutManager
            adapter = ChildTaskAdapter(listTask[position].listTask)
            setRecycledViewPool(viewPool)//fixme why we use that

        }
        itemTouchHelper.attachToRecyclerView(holder.childrv)
    }

    inner class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNoteItem = itemView.findViewById<TextView>(R.id.textNote)
        val childrv = itemView.findViewById<RecyclerView>(R.id.child_rv)


    }
}
