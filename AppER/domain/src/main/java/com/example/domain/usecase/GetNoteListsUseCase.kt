package com.example.domain.usecase

import com.example.domain.repository.NoteRepository

class GetNoteListsUseCase constructor (private val noteRepository: NoteRepository) {
    suspend fun invoke() = noteRepository.getNotes()
}