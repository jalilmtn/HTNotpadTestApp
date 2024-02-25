package com.example.htnotpadtestapp.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.htnotpadtestapp.domain.model.Note


@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DB_NAME = "my_note_db"
    }

}