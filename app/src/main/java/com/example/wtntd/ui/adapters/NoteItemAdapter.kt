package com.example.wtntd.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.TaskToDo
import com.example.wtntd.model.data.database.IGetDataBase
import com.example.wtntd.model.data.room.RoomTaskToDo
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteItemAdapter(
    val listNotes: List<RoomTaskToDo>,
    val onTaskClick: ((roomTaskToDo: RoomTaskToDo) -> Unit)? = null,
    val iGetDataBase: IGetDataBase
) :
    RecyclerView.Adapter<NoteItemAdapter.NoteItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.parent_recycler_view, parent, false)
        )


    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = listNotes[position].task
        holder.textNoteItem.setOnLongClickListener {
            onTaskClick?.invoke(listNotes[position])
            notifyDataSetChanged()
            iGetDataBase.insertSubToDo(
                SubRoomTaskToDo(
                    listNotes[position].id,
                    listNotes[position].id.toString()

                )
            )
            return@setOnLongClickListener true
        }


        val subList = mutableListOf<SubRoomTaskToDo>()
        iGetDataBase.getSubListTask(listNotes[position],subList)

        val subAdapter = SubNoteItemAdapter(subList)
        val linearLayoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL, false)

        holder.subRV.apply {
            layoutManager = linearLayoutManager
            adapter = subAdapter
        }
//        subAdapter.notifyDataSetChanged()

        holder.subRV.adapter?.notifyDataSetChanged()

    }

    class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNoteItem = itemView.findViewById<TextView>(R.id.textNote)
        val subRV = itemView.findViewById<RecyclerView>(R.id.sub_rv)


    }
}
