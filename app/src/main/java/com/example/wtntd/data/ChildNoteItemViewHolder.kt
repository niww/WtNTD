package com.example.wtntd.data

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

open class ChildNoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val childtextNoteItem = itemView.findViewById<TextView>(R.id.childTextNote)

}