package com.mmrbd.note_room_hilt_coroutine.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmrbd.note_room_hilt_coroutine.data.room.NoteDao
import com.mmrbd.note_room_hilt_coroutine.data.models.Note


@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}

