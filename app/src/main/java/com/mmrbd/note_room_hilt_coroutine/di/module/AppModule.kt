package com.mmrbd.note_room_hilt_coroutine.di.module

import android.content.Context
import androidx.room.Room
import com.mmrbd.note_room_hilt_coroutine.data.room.AppDatabase
import com.mmrbd.note_room_hilt_coroutine.data.room.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DATABASE_NAME = "note_database"

    @Singleton
    @Provides
    fun getRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }
}
