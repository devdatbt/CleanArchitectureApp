package com.example.domain.usecase

import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository

class DeleteNoteUseCase constructor(private val noteRepository: NoteRepository) {
    fun invoke(note: Note) = noteRepository.deleteNote(note)
}