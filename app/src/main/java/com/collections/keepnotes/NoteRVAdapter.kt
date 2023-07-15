package com.collections.keepnotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView


//initializing the layout
class NoteRVAdapter (
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface):RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNote = ArrayList<Note>()


inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val noteTV = itemView.findViewById<TextView>(R.id.TVNoteTitle)
    val timeTV = itemView.findViewById<TextView>(R.id.TVTimeStamp)
    val deleteIV = itemView.findViewById<ImageView>(R.id.IVDelete)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.noteTV.setText(allNote.get(position).noteTitle)
        holder.timeTV.setText("Last Updated : " +allNote.get(position).timestamp)

        holder.deleteIV.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClick(allNote.get(position))

        }
holder.itemView.setOnClickListener {
    noteClickInterface.onNoteClick(allNote.get(position))
      }
    }

    override fun getItemCount(): Int {
return  allNote.size
    }
fun updateList(newList: List<Note>){
    allNote.clear()
    allNote.addAll(newList)
    notifyDataSetChanged()
}
}

interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note: Note)
}

interface  NoteClickInterface{
fun onNoteClick(note: Note)
}