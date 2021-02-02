package com.example.wtntd.ui.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.example.wtntd.ui.activity.ListToDo
import kotlinx.android.synthetic.main.recycler_view.view.*

class NoteListItemAdapter(val listNotes: List<SubRoomTaskToDo>, val activity: ListToDo) :
    RecyclerView.Adapter<NoteListItemAdapter.NoteItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_list_todo, parent, false)
        )


    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = listNotes[position].task

        holder.llListToDo.setOnLongClickListener {

            activity.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.GRAY))
            activity.supportActionBar?.title = "$position"

            it.background = ColorDrawable(Color.GRAY)
            return@setOnLongClickListener true
        }



    }

    class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNoteItem = itemView.findViewById<TextView>(R.id.tv_todo)
        val llListToDo = itemView.findViewById<LinearLayout>(R.id.ll_list_todo)
        val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)
    }
}
