package com.example.apper.di.module

import com.example.data.repository.AccountServiceImpl
import com.example.data.repository.CurrencyRepositoryImpl
import com.example.data.repository.NoteRepositoryImpl
import com.example.domain.repository.AccountServiceRepository
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideNoteRepository(noteImpl: NoteRepositoryImpl): NoteRepository


    @Binds
    @Singleton
    abstract fun provideCurrencyRepository(currencyRepository: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    @Singleton
    abstract fun provideAccountServiceRepository(accountServiceImpl: AccountServiceImpl): AccountServiceRepository
}