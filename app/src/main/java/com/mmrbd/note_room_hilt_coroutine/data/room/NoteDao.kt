package com.mmrbd.note_room_hilt_coroutine.data.room

import androidx.room.*
import com.mmrbd.note_room_hilt_coroutine.data.models.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun getAllNote(): List<Note>

    @Delete
    suspend fun delete(note: Note)

}