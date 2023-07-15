package com.collections.keepnotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notesTable")
class Note (
    @ColumnInfo(name = "title")val noteTitle:String,
    @ColumnInfo(name = "description")val noteDescription:String,
    @ColumnInfo(name = "timestamp")val timestamp:String){
@PrimaryKey(autoGenerate = true)
var id = 0
}
//model class we will be using to adding all the data, updating and displaying the data in the recyclerview