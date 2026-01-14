package com.example.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Note

@Entity(tableName = "note_table")
data class NoteEntity(
    val title: String? = "",
    val content: String? = "",
    @PrimaryKey
    val timestamp: Long = 0,
    val userId: String? = "" // Liên kết đến UserEntity
) {

    fun toNote() = Note(title, content, timestamp, userId)

    companion object {
        fun fromNote(note: Note) = NoteEntity(
            note.title,
            note.content,
            note.timestamp,
            note.userId
        )
    }
}
