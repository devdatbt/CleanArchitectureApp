package com.example.apper.di.koin

import android.content.Context
import androidx.room.Room
import com.example.apper.NoteApplication
import com.example.data.datasource.local.NoteDao
import com.example.data.datasource.local.NoteDatabase
import com.example.data.utils.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    fun provideDb(context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    fun provideNoteDb(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()
    single { provideDb(androidContext()) }
    single { provideNoteDb(get()) }
}