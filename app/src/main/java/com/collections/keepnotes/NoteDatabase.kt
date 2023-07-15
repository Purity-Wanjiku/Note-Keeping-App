package com.collections.keepnotes

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext


//create a database class where we will store all data
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase  :RoomDatabase(){
    abstract  fun getNotesDao():NotesDataAccessObject  //retrieve DAO object that contains methods for the database to interact with

    companion object{  //a singleton-object that provides a way to access the database instance and ensures that only one instance of the database is created.


// declare a volatile variable named INSTANCE, which holds the singleton instance of the NoteDatabase class.
// The volatile keyword ensures that the value of the INSTANCE variable is always up-to-date and visible to all threads.
        @Volatile
        private var INSTANCE: NoteDatabase? = null


//Create and initialize the database instance.
        fun getDatabase(context:Context):NoteDatabase{
//Return the existing INSTANCE if it is not null. Otherwise, enter a synchronized block to ensure thread safety while creating the database instance.
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(  //build the database instance.
                    context.applicationContext,
                    NoteDatabase::class.java,  //Retrieve Class object
                    "note_database"  //name of the database
                ).build()                 //build and return the database instance created
                INSTANCE = instance    //assign the newly created database instance
                //return the created database instance.
                instance
            }
        }
    }
}