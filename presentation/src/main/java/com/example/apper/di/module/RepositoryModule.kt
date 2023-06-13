package com.example.apper.di.module

import com.example.apper.di.ApplicationScope
import com.example.data.repository.CurrencyRepositoryImpl
import com.example.data.repository.NoteRepositoryImpl
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    @ApplicationScope
    abstract fun provideNoteRepository(noteImpl: NoteRepositoryImpl): NoteRepository


    @Binds
    @ApplicationScope
    abstract fun provideCurrencyRepository(currencyRepository: CurrencyRepositoryImpl): CurrencyRepository
}