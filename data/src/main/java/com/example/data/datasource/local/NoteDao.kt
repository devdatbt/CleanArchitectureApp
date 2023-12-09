package com.example.data.datasource.local

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface NoteDao {
    @Query("select * from note_table")
    fun getNoteLists(): Flowable<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(entity: NoteEntity): Completable

    @Query("UPDATE note_table SET title = :title, content = :content WHERE timestamp = :time")
    fun updateNote(title: String, content: String, time: Long): Completable

    @Delete
    fun deleteNote(entity: NoteEntity): Single<Int>

    @Query("select * from note_table where timestamp=:id")
    fun getNoteWithId(id: Long): Flowable<NoteEntity>

    @Query("SELECT * FROM note_table ORDER BY timestamp ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int): List<NoteEntity>
}