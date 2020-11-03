package com.example.whntd.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R

class NoteItemAdapter(val listNotes:MutableList<String>): RecyclerView.Adapter<NoteItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {

        return NoteItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view,parent,false))
    }

    override fun getItemCount(): Int {
//        return list.size
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = listNotes[position]
    }


}