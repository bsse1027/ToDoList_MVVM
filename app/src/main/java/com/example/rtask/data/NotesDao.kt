package com.example.rtask.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Notes)

    @Update
    suspend fun update(note: Notes)

    @Delete
    suspend fun delete(note: Notes)

    @Query("SELECT * FROM notesTable order by id ASC" )
    fun getAllNotes():LiveData<List<Notes>>
}