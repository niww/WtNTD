package com.example.whntd.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R

class NoteItemAdapter: RecyclerView.Adapter<NoteItemViewHolder>() {
    val list = mutableListOf<String>("12","sdsd","Work")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {

        return NoteItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = list[position].toString()
    }


}