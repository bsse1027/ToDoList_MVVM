package com.example.rtask.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rtask.*
import com.example.rtask.adapter.NoteClickDeleteInterface
import com.example.rtask.adapter.NoteClickInterface
import com.example.rtask.adapter.NoteRVAdapater
import com.example.rtask.data.Notes
import com.example.rtask.viewModel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    lateinit var notesRV: RecyclerView
    lateinit var addButton : FloatingActionButton
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesRV = findViewById(R.id.notesRV)
        addButton = findViewById(R.id.idFAB)
        notesRV.layoutManager = LinearLayoutManager(this)
        val noteRVAdapater = NoteRVAdapater(this ,this , this)
        notesRV.adapter = noteRVAdapater
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        viewModel.allNotes.observe(this, { list ->
            list?.let {
                noteRVAdapater.updateList(it)
            }
        })
        addButton.setOnClickListener {
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(notes: Notes) {
        val intent = Intent(this@MainActivity, EditActivity::class.java)
        intent.putExtra("noteType" , "Edit")
        intent.putExtra("noteTitle", notes.taskTitle)
        intent.putExtra("noteDescription", notes.taskDetails)
        intent.putExtra("noteID",notes.id)
        startActivity(intent)
        this.finish()

    }


    override fun onDeleteClick(notes: Notes) {
        viewModel.deleteNode(notes)
        Toast.makeText(this , "${notes.taskTitle} deleted" , Toast.LENGTH_LONG).show()
    }


}