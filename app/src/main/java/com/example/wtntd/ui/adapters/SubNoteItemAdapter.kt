package com.example.wtntd.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.room.SubRoomTaskToDo

class SubNoteItemAdapter(val subList:List<SubRoomTaskToDo>) :
    RecyclerView.Adapter<SubNoteItemAdapter.ChildNoteItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ChildNoteItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.child_recycler_view, parent, false)
        )

    override fun getItemCount(): Int {
        return subList.size
    }

    override fun onBindViewHolder(holder: ChildNoteItemViewHolder, position: Int) {

        holder.childtextNoteItem.text = subList[position].task
    }

    class ChildNoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val childtextNoteItem = itemView.findViewById<TextView>(R.id.childTextNote)

    }

}