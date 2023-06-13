package com.example.data.repository

import com.example.data.datasource.local.NoteDao
import com.example.data.datasource.local.NoteEntity
import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NoteRepositoryImpl constructor(private val noteDao: NoteDao) : NoteRepository {
    override fun getNoteLists(): Flowable<List<Note>> = noteDao.getNoteLists()
        .subscribeOn(Schedulers.io())
        .map {
            it.map { enRichEntity ->
                enRichEntity.toNote()
            }
        }

    override fun insertNote(note: Note): Completable {
        val noteEntity = NoteEntity.fromNote(note)
        return noteDao.insertNote(noteEntity).subscribeOn(Schedulers.io())
    }

    override fun updateNote(title: String, content: String, time: Long): Completable {
        return noteDao.updateNote(title = title, content = content, time = time)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteNote(note: Note): Single<Int> {
        val noteEntity = NoteEntity.fromNote(note)
        return noteDao.deleteNote(noteEntity).subscribeOn(Schedulers.io())
    }

    override fun getNoteWithId(id: Long): Flowable<Note> {
        return noteDao.getNoteWithId(id).subscribeOn(Schedulers.io())
            .map {
                it.toNote()
            }.onBackpressureDrop()
    }
}