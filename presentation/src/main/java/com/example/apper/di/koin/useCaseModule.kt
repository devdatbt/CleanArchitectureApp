package com.example.apper.di.koin

import com.example.apper.usecase.AppUseCase
import com.example.data.datasource.local.NoteDao
import com.example.data.datasource.remote.CurrencyApiService
import com.example.data.repository.CurrencyRepositoryImpl
import com.example.data.repository.NoteRepositoryImpl
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.NoteRepository
import com.example.domain.usecase.GetCurrencyUseCase
import com.example.domain.usecase.GetNoteListsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    //use case note room db
    single { provideNoteRepository(get()) }
    single { provideGetNoteListsUseCase(get()) }

    //use case currency api
    single { provideCurrencyRepository(get()) }
    single { provideGetCurrencyUseCase(get()) }
    single { provideGetAppUseCase(get(), get()) }
}

fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
    return NoteRepositoryImpl(noteDao)
}

fun provideGetNoteListsUseCase(noteRepository: NoteRepository): GetNoteListsUseCase {
    return GetNoteListsUseCase(noteRepository)
}

fun provideCurrencyRepository(currencyApiService: CurrencyApiService): CurrencyRepository {
    return CurrencyRepositoryImpl(currencyApiService)
}

fun provideGetCurrencyUseCase(currencyRepository: CurrencyRepository): GetCurrencyUseCase {
    return GetCurrencyUseCase(currencyRepository)
}

fun provideGetAppUseCase(
    getNoteListsUseCase: GetNoteListsUseCase,
    getCurrencyUseCase: GetCurrencyUseCase
): AppUseCase {
    return AppUseCase(getNoteListsUseCase, getCurrencyUseCase)
}