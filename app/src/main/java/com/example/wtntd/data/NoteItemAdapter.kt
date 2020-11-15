package com.example.wtntd.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whntd.data.NoteItemViewHolder
import com.example.wtntd.R

class NoteItemAdapter(val listNotes:MutableList<String>): RecyclerView.Adapter<NoteItemViewHolder>() {

    val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {

        return NoteItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.parent_recycler_view,parent,false))
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = listNotes[position]
        holder.textNoteItem.setOnLongClickListener { view -> listNotes.remove(listNotes[holder.adapterPosition]) }
        //child rv
        val childLayoutManager = LinearLayoutManager(holder.childrv.context,LinearLayoutManager.VERTICAL,false)
        holder.childrv.apply {
            layoutManager =childLayoutManager
            adapter = ChildNoteItemAdapter(listNotes)
            setRecycledViewPool(viewPool)//fixme why we use that

        }


    }


}