package com.example.wtntd.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.TaskToDo
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteItemAdapter(val listNotes: List<RoomTaskToDo>, val onTaskClick: ((roomTaskToDo: RoomTaskToDo)-> Unit)?= null ) :
    RecyclerView.Adapter<NoteItemAdapter.NoteItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.parent_recycler_view, parent, false)
        )


    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = listNotes[position].task
        holder.textNoteItem.setOnLongClickListener {
            onTaskClick?.invoke(listNotes[position])
            notifyDataSetChanged()
            return@setOnLongClickListener true
        }

    }

    class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNoteItem = itemView.findViewById<TextView>(R.id.textNote)
        val subRV =  itemView.findViewById<RecyclerView>(R.id.sub_rv)

    }
}
