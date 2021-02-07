package com.example.wtntd.ui.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.*
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.RecyclerView
import com.example.wtntd.R
import com.example.wtntd.model.data.database.GetDataBase
import com.example.wtntd.model.data.room.SubRoomTaskToDo
import com.example.wtntd.ui.activity.ListToDoActivity
import kotlinx.android.synthetic.main.recycler_view.view.*

class NoteListItemAdapter(
    val listNotes: List<SubRoomTaskToDo>,
    val activity: ListToDoActivity,
    val onClickToDo: ((s: SubRoomTaskToDo) -> Unit)? = null
) :
    RecyclerView.Adapter<NoteListItemAdapter.NoteItemViewHolder>(),
    ActionMode.Callback {

    var multiSelector = false
    val selectedToDo = mutableListOf<SubRoomTaskToDo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_list_todo, parent, false)
        )


    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {

        holder.textNoteItem.text = listNotes[position].task
        holder.llListToDo.background = ColorDrawable(Color.WHITE)

        holder.checkBox.isChecked = listNotes[position].isChecked
        if (holder.checkBox.isChecked) {
            holder.textNoteItem.setTextColor(Color.RED)
        }

        val currentTodo = listNotes[position]
        if (selectedToDo.contains(currentTodo)) {
            holder.llListToDo.background = ColorDrawable(Color.GRAY)
        }

        holder.llListToDo.setOnLongClickListener {

            if (!multiSelector) {
                multiSelector = true
                activity.supportActionBar?.hide()
                activity.startSupportActionMode(this)
                selectTodo(holder, listNotes[position])
            }
            return@setOnLongClickListener true
        }
        holder.llListToDo.setOnClickListener {
            if (multiSelector) {
                selectTodo(holder, listNotes[position])
            }
        }

        holder.checkBox.setOnClickListener {
            when ((it as CheckBox).isChecked) {
                true -> {
                    holder.textNoteItem.setTextColor(Color.RED)
                }
                false -> {
                    holder.textNoteItem.setTextColor(Color.GRAY)
                }
            }

            onClickToDo?.invoke(listNotes[position])
        }

    }

    class NoteItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNoteItem = itemView.findViewById<TextView>(R.id.tv_todo)
        val llListToDo = itemView.findViewById<LinearLayout>(R.id.ll_list_todo)
        val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)

    }

    fun selectTodo(holder: NoteItemViewHolder, s: SubRoomTaskToDo) {
        if (selectedToDo.contains(s)) {
            selectedToDo.remove(s)
            holder.llListToDo.background = ColorDrawable(Color.WHITE)
        } else {
            selectedToDo.add(s)
            holder.llListToDo.background = ColorDrawable(Color.GRAY)
        }

    }

    override fun onCreateActionMode(
        mode: ActionMode?,
        menu: Menu?
    ): Boolean {
        val inflater: MenuInflater = mode!!.menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onPrepareActionMode(
        mode: ActionMode?,
        menu: Menu?
    ): Boolean {
        return true
    }

    override fun onActionItemClicked(
        mode: ActionMode?,
        item: MenuItem?
    ): Boolean {
        if (item!!.itemId == R.id.action_delete) {
            Toast.makeText(activity.applicationContext, "Selected ToDo deleted", Toast.LENGTH_SHORT)
                .show()
            GetDataBase().deleteSubToDo(selectedToDo)

            mode!!.finish()
        }
        if (item.itemId == R.id.home) {
            multiSelector = false
            selectedToDo.clear()
            notifyDataSetChanged()
            mode!!.finish()
            activity.supportActionBar?.show()

        }

        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        multiSelector = false
        selectedToDo.clear()
        activity.supportActionBar?.show()
        notifyDataSetChanged()
    }
}
