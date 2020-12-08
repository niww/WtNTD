package com.example.wtntd.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R

class ChildNoteItemAdapter(val listNotes: List<String>) :
    RecyclerView.Adapter<ChildNoteItemAdapter.ChildNoteItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildNoteItemViewHolder {

        return ChildNoteItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.child_recycler_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: ChildNoteItemViewHolder, position: Int) {

        holder.childtextNoteItem.text = listNotes[position]
    }

    inner class ChildNoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val childtextNoteItem = itemView.findViewById<TextView>(R.id.childTextNote)

    }

}