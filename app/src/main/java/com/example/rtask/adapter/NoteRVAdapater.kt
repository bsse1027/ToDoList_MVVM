package com.example.rtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rtask.R
import com.example.rtask.data.Notes

class NoteRVAdapater(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
                     ): RecyclerView.Adapter<NoteRVAdapater.ViewHolder>() {

    val allNotes = ArrayList<Notes>()

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

            val noteTextView = itemView.findViewById<TextView>(R.id.idTVNote)
            val noteTime = itemView.findViewById<TextView>(R.id.idTVDate)
            val noteDeletView = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTextView.setText(allNotes.get(position).taskTitle)
        holder.noteTime.setText(allNotes.get(position).timeStamp)

        holder.noteDeletView.setOnClickListener{
            noteClickDeleteInterface.onDeleteClick(allNotes.get(position))
        }
        holder.itemView.setOnClickListener{
            noteClickInterface.onNoteClick(allNotes.get(position))
        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}


interface NoteClickDeleteInterface{
    fun onDeleteClick(notes: Notes)
}

interface  NoteClickInterface{
    fun onNoteClick(notes: Notes)
}