package com.example.wtntd.ui.adapters

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.google.rpc.context.AttributeContext

class NoteItemAdapter(val listNotes: List<RoomTaskToDo>, val clickOnTask: ((roomTaskToDo: RoomTaskToDo)-> Unit)?= null ) :
    RecyclerView.Adapter<NoteItemAdapter.NoteItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view, parent, false)
        )


    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = listNotes[position].task
        holder.tvListStatus.text = "${listNotes[position].openToDo}/${listNotes[position].closedToDo}"

        holder.cardView.setOnClickListener {
            clickOnTask?.invoke(listNotes[position])
        }

    }

    class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNoteItem = itemView.findViewById<TextView>(R.id.textNote)
        val tvListStatus = itemView.findViewById<TextView>(R.id.tv_status)
        val cardView = itemView.findViewById<CardView>(R.id.card_view)

    }
}
