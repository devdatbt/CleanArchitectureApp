package com.example.domain.usecase

import com.example.domain.repository.NoteRepository

class UpdateNoteUseCase constructor(private val noteRepository: NoteRepository) {
    fun invoke(title: String, content: String, time: Long) =
        noteRepository.updateNote(title, content, time)
}