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
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteItemAdapter(val listNotes: MutableList<String>, val context: Context) :
    RecyclerView.Adapter<NoteItemAdapter.NoteItemViewHolder>() {

    val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.parent_recycler_view, parent, false)
        )


    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val nestedList = mutableListOf<String>()

        nestedList.add(listNotes[position])
        holder.textNoteItem.text = listNotes[position]
        holder.textNoteItem.setOnLongClickListener {

            val editText = EditText(context)
            MaterialAlertDialogBuilder(context)
                .setTitle("Nested ToDo")
                .setView(editText)
                .setNegativeButton("Cancel", { dialogInterface, i -> "Ok" })
                .setPositiveButton(
                    "Ok"
                ) { dialogInterface, i -> nestedList.add(editText.text.toString()) }
                .show()

            return@setOnLongClickListener true
        }

        //child rv
        val childLayoutManager =
            LinearLayoutManager(holder.childrv.context, LinearLayoutManager.VERTICAL, false)
        holder.childrv.apply {
            layoutManager = childLayoutManager
            adapter = ChildNoteItemAdapter(nestedList)
            setRecycledViewPool(viewPool)//fixme why we use that
        }
    }

    class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNoteItem = itemView.findViewById<TextView>(R.id.textNote)
        val childrv = itemView.findViewById<RecyclerView>(R.id.child_rv)

    }
}
