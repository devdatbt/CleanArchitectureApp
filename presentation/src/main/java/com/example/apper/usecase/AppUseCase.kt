package com.example.apper.usecase

import com.example.domain.usecase.*
import javax.inject.Inject

data class AppUseCase @Inject constructor(
    val addNoteUseCase: AddNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getNoteListsUseCase: GetNoteListsUseCase,
    val getNoteWithIdUseCase: GetNoteWithIdUseCase,
    val updateNoteUseCase: UpdateNoteUseCase,
    val getCurrencyUseCase: GetCurrencyUseCase
)
