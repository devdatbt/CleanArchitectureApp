package com.example.apper.di.module

import android.app.Application
import com.example.apper.di.ApplicationScope
import com.example.data.datasource.local.NoteDao
import com.example.data.datasource.local.NoteDatabase
import dagger.Module
import dagger.Provides

@Module
object DBModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(application: Application): NoteDatabase = NoteDatabase.getInstance(application)

    @Provides
    @ApplicationScope
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()

}