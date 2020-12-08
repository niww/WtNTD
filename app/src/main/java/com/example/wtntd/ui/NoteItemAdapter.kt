package com.example.wtntd.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.data.TaskToDo
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteItemAdapter(val listTaskToDo:  List<TaskToDo>, val context: Context) :
    RecyclerView.Adapter<NoteItemAdapter.NoteItemViewHolder>() {

    val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {

        return NoteItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.parent_recycler_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listTaskToDo.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = listTaskToDo[position].task

        val childLayoutManager =
            LinearLayoutManager(holder.childrv.context, LinearLayoutManager.VERTICAL, false)
        holder.childrv.apply {
            layoutManager = childLayoutManager
            adapter = ChildNoteItemAdapter(listTaskToDo[position].listTask)
            setRecycledViewPool(viewPool)//fixme why we use that
        }
    }
    inner class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNoteItem = itemView.findViewById<TextView>(R.id.textNote)
        val childrv = itemView.findViewById<RecyclerView>(R.id.child_rv)

    }
}
