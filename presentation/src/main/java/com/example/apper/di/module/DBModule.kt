package com.example.apper.di.module

import android.app.Application
import com.example.data.datasource.local.NoteDao
import com.example.data.datasource.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): NoteDatabase = NoteDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()

}