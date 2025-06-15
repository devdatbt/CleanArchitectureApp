package com.example.apper.usecase

import com.example.domain.usecase.AddItemToFireStoreUseCase
import com.example.domain.usecase.AddNoteUseCase
import com.example.domain.usecase.AutheticateUseCase
import com.example.domain.usecase.DeleteItemToFireStoreUseCase
import com.example.domain.usecase.DeleteNoteUseCase
import com.example.domain.usecase.GetCurrencyUseCase
import com.example.domain.usecase.GetNoteListsUseCase
import com.example.domain.usecase.GetNoteWithIdUseCase
import com.example.domain.usecase.SignOutUseCase
import com.example.domain.usecase.UpdateNoteUseCase

data class AppUseCase(
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
