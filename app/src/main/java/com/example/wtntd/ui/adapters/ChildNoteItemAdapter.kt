package com.example.wtntd.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R

class ChildNoteItemAdapter(val listNotes: MutableList<String>) :
    RecyclerView.Adapter<ChildNoteItemAdapter.ChildNoteItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ChildNoteItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.child_recycler_view, parent, false)
        )

    override fun getItemCount(): Int {
        return listNotes.size
//        return Random.nextInt() //fixme
//        return 1
    }

    override fun onBindViewHolder(holder: ChildNoteItemViewHolder, position: Int) {

        holder.childtextNoteItem.text = listNotes[position]
    }

    class ChildNoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val childtextNoteItem = itemView.findViewById<TextView>(R.id.childTextNote)

    }

}