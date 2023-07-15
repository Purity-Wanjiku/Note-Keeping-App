package com.collections.keepnotes

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
//specify all the methods that will perform actions suc as insert, update, delete, and read
interface NotesDataAccessObject {
                //perform basic CRUD operations

   //insert operation
//specify the conflict resolution strategy when there is a conflict with the inserted data.
//The suspend keyword is used to mark the function as a suspend function,
// which means it can be safely called from a coroutine or another suspend function
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun  insert(note: Note):Long



//Delete operation
//Add function responsible for deleting a specific Note object from the database.
   @Delete
   suspend fun delete(note: Note)



//Query selects all rows from the notesTable table and orders them by the id column in ascending order.
//Add function used to retrieve all notes from the database.
   @Query("Select * from notesTable order by id ASC")
   fun getAllNotes():LiveData<List<Note>>


   //Update operation
//Add function responsible for updating an existing Note object in the database.
   @Update
   suspend fun  update(note: Note)

}