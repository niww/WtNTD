package com.example.wtntd.data

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whntd.data.NoteItemViewHolder
import com.example.wtntd.MainActivity
import com.example.wtntd.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteItemAdapter(val listNotes: MutableList<String>) :
    RecyclerView.Adapter<NoteItemViewHolder>() {

    val viewPool = RecyclerView.RecycledViewPool()
    val nestedList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {

        return NoteItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.parent_recycler_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        nestedList.add(listNotes[position])
        holder.textNoteItem.text = listNotes[position]
        holder.textNoteItem.setOnLongClickListener { view -> listNotes.remove(listNotes[holder.adapterPosition]) }
        //child rv
        val childLayoutManager =
            LinearLayoutManager(holder.childrv.context, LinearLayoutManager.VERTICAL, false)
        holder.childrv.apply {
            layoutManager = childLayoutManager
            adapter = ChildNoteItemAdapter(nestedList)
            setRecycledViewPool(viewPool)//fixme why we use that
        }


    }


}