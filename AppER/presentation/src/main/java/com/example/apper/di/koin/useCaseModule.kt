package com.example.apper.di.koin

import com.example.apper.usecase.AppUseCase
import com.example.data.repository.AccountServiceImpl
import com.example.data.repository.CurrencyRepositoryImpl
import com.example.data.repository.NoteRepositoryImpl
import com.example.domain.repository.AccountServiceRepository
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.NoteRepository
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
import org.koin.dsl.module

val useCaseModule = module {
    //repository
    single<NoteRepository> { NoteRepositoryImpl(get()) }
    single<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
    single<AccountServiceRepository> { AccountServiceImpl(get(), get()) }

    //use case note room db
    single { provideGetNoteListsUseCase(get()) }
    single { provideNoteWithIdUseCase(get()) }
    single { provideDeleteNoteUseCase(get()) }
    single { provideAddNoteUseCase(get()) }
    single { provideUpdateNoteUseCase(get()) }
    //use case currency api
    single { provideGetCurrencyUseCase(get()) }
    //use case firebase api
    single { provideAutheticateUseCase(get()) }
    single { provideSignOutUseCase(get()) }
    single { provideAddItemToFireStoreUseCase(get()) }
    single { provideDeleteItemToFireStoreUseCase(get()) }

    //get all use case
    single {
        provideGetAppUseCase(
            get(), get(), get(), get(), get(), get(), get(), get(), get(), get()
        )
    }
}

//provide note use case
fun provideGetNoteListsUseCase(noteRepository: NoteRepository): GetNoteListsUseCase =
    GetNoteListsUseCase(noteRepository)

fun provideNoteWithIdUseCase(noteRepository: NoteRepository) = GetNoteWithIdUseCase(noteRepository)
fun provideDeleteNoteUseCase(noteRepository: NoteRepository) = DeleteNoteUseCase(noteRepository)
fun provideAddNoteUseCase(noteRepository: NoteRepository) = AddNoteUseCase(noteRepository)
fun provideUpdateNoteUseCase(noteRepository: NoteRepository) = UpdateNoteUseCase(noteRepository)

fun provideGetCurrencyUseCase(currencyRepository: CurrencyRepository): GetCurrencyUseCase =
    GetCurrencyUseCase(currencyRepository)

//provide account firebase use case
fun provideAutheticateUseCase(accountServiceRepository: AccountServiceRepository) =
    AutheticateUseCase(accountServiceRepository)

fun provideSignOutUseCase(accountServiceRepository: AccountServiceRepository) =
    SignOutUseCase(accountServiceRepository)

fun provideAddItemToFireStoreUseCase(accountServiceRepository: AccountServiceRepository) =
    AddItemToFireStoreUseCase(accountServiceRepository)

fun provideDeleteItemToFireStoreUseCase(accountServiceRepository: AccountServiceRepository) =
    DeleteItemToFireStoreUseCase(accountServiceRepository)

fun provideGetAppUseCase(
    addNoteUseCase: AddNoteUseCase,
    deleteNoteUseCase: DeleteNoteUseCase,
    getNoteListsUseCase: GetNoteListsUseCase,
    getNoteWithIdUseCase: GetNoteWithIdUseCase,
    updateNoteUseCase: UpdateNoteUseCase,
    getCurrencyUseCase: GetCurrencyUseCase,
    autheticateUseCase: AutheticateUseCase,
    signOutUseCase: SignOutUseCase,
    addItemToFireStoreUseCase: AddItemToFireStoreUseCase,
    deleteItemToFireStoreUseCase: DeleteItemToFireStoreUseCase,
): AppUseCase {
    return AppUseCase(
        addNoteUseCase = addNoteUseCase,
        deleteNoteUseCase = deleteNoteUseCase,
        getNoteListsUseCase = getNoteListsUseCase,
        getNoteWithIdUseCase = getNoteWithIdUseCase,
        updateNoteUseCase = updateNoteUseCase,
        getCurrencyUseCase = getCurrencyUseCase,
        autheticateUseCase = autheticateUseCase,
        signOutUseCase = signOutUseCase,
        addItemToFireStoreUseCase = addItemToFireStoreUseCase,
        deleteItemToFireStoreUseCase = deleteItemToFireStoreUseCase
    )
}