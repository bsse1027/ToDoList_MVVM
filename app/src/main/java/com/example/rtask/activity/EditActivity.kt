package com.example.rtask.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.rtask.viewModel.NoteViewModel
import com.example.rtask.R
import com.example.rtask.data.Notes
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity() {

    lateinit var noteTitleEdit : EditText
    lateinit var noteDescEdit : EditText
    lateinit var editBtn: Button
    lateinit var viewModel: NoteViewModel
    var noteID =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        noteTitleEdit = findViewById(R.id.EditTitle)
        noteDescEdit= findViewById(R.id.EditDesc)
        editBtn = findViewById(R.id.editButton)
        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            NoteViewModel::class.java)
        val noteType = intent.getStringExtra("noteType")

        if(noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID", -1)
            editBtn.setText("Update")
            noteTitleEdit.setText(noteTitle)
            noteDescEdit.setText(noteDesc)
        }
        else {
            editBtn.setText("Save")
        }

        editBtn.setOnClickListener {
            val noteTitle = noteTitleEdit.text.toString()
            val noteDesc = noteDescEdit.text.toString()

            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val date = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = date.format(Date())
                    val updatedNote = Notes(noteTitle, noteDesc , currentDate,noteID)
                    //updatedNote.id = noteID
                    viewModel.updateNode(updatedNote)
                }
            }
            else{
                if(noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val date = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = date.format(Date())
                    viewModel.addNote(Notes(noteTitle , noteDesc , currentDate))
                    Toast.makeText(this , "Note Added..", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java ))
            this.finish()
        }

    }
}