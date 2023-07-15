package com.collections.keepnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collections.keepnotes.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesRV = binding.RVNotes
        addFAB = binding.FABAddNote
        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this,this,this)
        notesRV.adapter = noteRVAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)
        viewModel.allNote.observe(this, Observer { list ->
            list?.let{
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener{
            val intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }



    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} Deleted",Toast.LENGTH_LONG).show()
    }
}