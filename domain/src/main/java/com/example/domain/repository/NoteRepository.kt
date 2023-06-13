package com.example.domain.repository

import com.example.domain.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface NoteRepository {
    fun getNoteLists(): Flowable<List<Note>>
    fun insertNote(note: Note): Completable
    fun updateNote(title: String, content: String, time: Long): Completable
    fun deleteNote(note: Note): Single<Int>
    fun getNoteWithId(id: Long): Flowable<Note>
}