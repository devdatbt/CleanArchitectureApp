package com.example.apper.usecase

import com.example.domain.usecase.*
import javax.inject.Inject
import javax.inject.Singleton

data class AppUseCase @Inject constructor(
    val addNoteUseCase: AddNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getNoteListsUseCase: GetNoteListsUseCase,
    val getNoteWithIdUseCase: GetNoteWithIdUseCase,
    val updateNoteUseCase: UpdateNoteUseCase,
    val getCurrencyUseCase: GetCurrencyUseCase,
    val autheticateUseCase: AutheticateUseCase,
    val signOutUseCase: SignOutUseCase,
    val addItemToFireStoreUseCase: AddItemToFireStoreUseCase,
    val deleteItemToFireStoreUseCase: DeleteItemToFireStoreUseCase,
)
