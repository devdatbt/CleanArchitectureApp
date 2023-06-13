package com.example.domain.usecase

import com.example.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteListsUseCase constructor (private val noteRepository: NoteRepository) {
    fun invoke() = noteRepository.getNoteLists()
}