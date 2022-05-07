package com.example.rtask.data
import androidx.lifecycle.LiveData

class NoteRepository (private val notesDao: NotesDao) {

    val getAllNodes : LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insert(note: Notes)
    {
        notesDao.insert(note)
    }

    suspend fun update(note: Notes)
    {
        notesDao.update(note)
    }

    suspend fun delete(note: Notes)
    {
        notesDao.delete(note)
    }

}