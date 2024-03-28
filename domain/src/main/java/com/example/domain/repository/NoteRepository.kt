package com.example.domain.repository

import com.example.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    val getAllNotes: Flow<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun updateNote(title: String, content: String, time: Long)
    suspend fun deleteNote(note: Note)
    fun getNoteWithId(id: Long): Flow<Note>
}