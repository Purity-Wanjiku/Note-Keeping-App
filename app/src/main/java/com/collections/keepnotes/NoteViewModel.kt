package com.collections.keepnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//create a viewmodel class, the main class that will be interacting with the main activity to get the methods  that perform all operations
//Communication bridge between the UI components (like activities or fragments) and the underlying data sources, such as a database or network.
//Provides a clean interface for the UI components to access and observe the data.
class NoteViewModel (application: Application): AndroidViewModel(application){
    val allNote: LiveData<List<Note>>  //The LiveData holds a list of Note objects and can be observed for changes.
    val repository:NoteRepository  // a custom class responsible for managing data operations and serves as an intermediary between the ViewModel and the data source (NoteDatabase)

    init {
val dao = NoteDatabase.getDatabase(application).getNotesDao()  //obtain the DAO instance from the database using application context
//Create an instance of the NoteRepository class, passing the DAO as a parameter. The repository is initialized with the DAO.
        repository = NoteRepository(dao)
//Assign the allNotes property of the NoteRepository to the allNote property of the ViewModel.
// This allows the UI components observing allNote to receive updates when the data changes
        allNote = repository.allNotes
    }

//    deleting, adding and updating all notes operations
    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
    repository.delete(note)
}

    fun updateNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }

    fun addNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }
}

//use the view model class inside the main activity file to access all the methods