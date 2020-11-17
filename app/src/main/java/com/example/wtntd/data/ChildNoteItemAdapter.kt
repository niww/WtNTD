package com.example.wtntd.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whntd.data.NoteItemViewHolder
import com.example.wtntd.R
import kotlin.random.Random

class ChildNoteItemAdapter(val listNotes:MutableList<String>): RecyclerView.Adapter<ChildNoteItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildNoteItemViewHolder {

        return ChildNoteItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.child_recycler_view,parent,false))
    }

    override fun getItemCount(): Int {
        return listNotes.size
//        return Random.nextInt() //fixme
//        return 1
    }

    override fun onBindViewHolder(holder: ChildNoteItemViewHolder, position: Int) {

        holder.childtextNoteItem.text = listNotes[position]
    }


}