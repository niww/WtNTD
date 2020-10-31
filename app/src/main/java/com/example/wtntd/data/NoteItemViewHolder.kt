package com.example.whntd.data

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R

open class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textNoteItem = itemView.findViewById<TextView>(R.id.textNote)

}