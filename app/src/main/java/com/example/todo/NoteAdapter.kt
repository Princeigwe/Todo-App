package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.models.Note

class NoteAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var noteItems: MutableList<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
           is NoteViewHolder -> {
                holder.bind(noteItems[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return noteItems.size
    }
    fun submitList(noteList: MutableList<Note>){
        noteItems = noteList
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var noteTitle: TextView = itemView.findViewById(R.id.note_title)
        var noteWriteUp: TextView = itemView.findViewById(R.id.note_writeup)
        var noteTime: TextView = itemView.findViewById(R.id.note_time)

        fun bind(note: Note){
            noteTitle.text = note.title
            noteWriteUp.text = note.writeup
            noteTime.text = note.time
        }
    }
}