package com.example.notekeeper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerAdaper(private val context: Context, private val notes: List<NoteInfo>) :
    RecyclerView.Adapter<NoteRecyclerAdaper.ViewHolder>(){

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = layoutInflater.inflate(R.layout.item_note, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val note = notes[position]
        holder.textCourse?.text = note.course?.title
        holder.textTitle?.text = note.title

        holder.notePosition = position

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textCourse = itemView.findViewById<TextView?>(R.id.text_course)
        val textTitle = itemView.findViewById<TextView?>(R.id.text_title)
        var notePosition = 0

        init {
            itemView.setOnClickListener{

                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra(NOTE_POSITION, notePosition)
                context.startActivity(intent)
            }
        }
    }
}