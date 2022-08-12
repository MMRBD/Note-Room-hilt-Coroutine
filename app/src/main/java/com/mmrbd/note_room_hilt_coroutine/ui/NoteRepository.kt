package com.mmrbd.note_room_hilt_coroutine.ui

import com.mmrbd.note_room_hilt_coroutine.data.room.NoteDao
import com.mmrbd.note_room_hilt_coroutine.data.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun getAllNote(): Flow<List<Note>> = flow {
        emit(noteDao.getAllNote())
    }
}