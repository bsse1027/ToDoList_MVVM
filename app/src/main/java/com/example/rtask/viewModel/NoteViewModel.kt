package com.example.rtask.viewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rtask.data.NoteDatabase
import com.example.rtask.data.NoteRepository
import com.example.rtask.data.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Notes>>
    val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.getAllNodes

    }

    fun deleteNode(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun updateNode(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
    fun addNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}