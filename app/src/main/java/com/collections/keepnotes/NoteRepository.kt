package com.collections.keepnotes

import androidx.lifecycle.LiveData



import java.util.concurrent.Flow
import androidx.annotation.WorkerThread

//repository class that will handle the list methods (update, insert, delete) for the DAO class
class NoteRepository (private val notesDataAccessObject: NotesDataAccessObject){



    //4 methods: get all notes, insert, delete and update operations
  val allNotes: LiveData<List<Note>> = notesDataAccessObject.getAllNotes()

  suspend fun insert(note: Note){
      notesDataAccessObject.insert((note))
  }

    suspend fun delete(note: Note){
        notesDataAccessObject.delete(note)
    }

    suspend fun update(note: Note){
        notesDataAccessObject.update(note)
    }
}
