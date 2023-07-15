package com.collections.keepnotes

import android.content.Intent
import android.icu.text.CaseMap
import android.icu.text.SimpleDateFormat
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var noteTitleEdt: EditText
    lateinit var noteDescriptionEdt: EditText
    lateinit var addupdateBtn: Button
    lateinit var viewModel: NoteViewModel
    var noteID = -1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

noteDescriptionEdt = findViewById(R.id.EdtNoteDescription)
        noteTitleEdt = findViewById(R.id.EdtNoteTitle)
    addupdateBtn = findViewById(R.id.BtnAddUpdate)
        viewModel =ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        AndroidThreeTen.init(this)

        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            //get value using the instance intent
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID",-1)
            addupdateBtn.setText("Update Note")
            noteTitleEdt.setText(noteTitle)
            noteDescriptionEdt.setText(noteDesc)
        }else{
            addupdateBtn.setText("Save Note")
        }
        addupdateBtn.setOnClickListener{
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteDescriptionEdt.text.toString()

            if (noteType.equals("Edit")){
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){


                    val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy - HH:mm")
                    val currentDate = LocalDateTime.now().format(formatter)

//                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
//                    val currentDate:String = sdf.format(Date())
                    val updateNote = Note(noteTitle,noteDescription,currentDate)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this,"Note Updated",Toast.LENGTH_LONG).show()
                }
            }else{
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
//                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
//                    val currentDate:String = sdf.format(Date())
                    val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy - HH:mm")
                    val currentDate = LocalDateTime.now().format(formatter)
                    viewModel.addNote(Note(noteTitle,noteDescription,currentDate))
                    Toast.makeText(this,"$noteTitle Added..",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }
    }
}