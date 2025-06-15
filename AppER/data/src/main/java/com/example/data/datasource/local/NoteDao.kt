package com.example.data.datasource.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("select * from note_table")
    fun getNoteLists(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(entity: NoteEntity)

    @Query("UPDATE note_table SET title = :title, content = :content WHERE timestamp = :time")
    suspend fun updateNote(title: String, content: String, time: Long)

    @Delete
    suspend fun deleteNote(entity: NoteEntity)

    @Query("select * from note_table where timestamp=:id")
    fun getNoteWithId(id: Long): Flow<NoteEntity>
}