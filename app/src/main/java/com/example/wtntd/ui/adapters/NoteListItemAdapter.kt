package com.example.wtntd.ui.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.model.data.room.SubRoomTaskToDo

class NoteListItemAdapter(val listNotes: List<SubRoomTaskToDo>, val clickOnTask: ((subRoomTaskToDo: SubRoomTaskToDo)-> Unit)?= null ) :
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

            it.background = ColorDrawable(Color.GRAY)
            return@setOnLongClickListener true
        }


    }

    class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNoteItem = itemView.findViewById<TextView>(R.id.tv_todo)

        val llListToDo = itemView.findViewById<LinearLayout>(R.id.ll_list_todo)

    }
}
