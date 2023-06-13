package com.example.domain.usecase

import com.example.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    fun invoke(title: String, content: String, time: Long) =
        noteRepository.updateNote(title, content, time)
}