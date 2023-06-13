package com.example.domain.usecase

import com.example.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteWithIdUseCase constructor(private val noteRepository: NoteRepository) {
    fun invoke(id: Long) = noteRepository.getNoteWithId(id)
}