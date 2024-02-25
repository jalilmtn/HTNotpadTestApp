package com.example.htnotpadtestapp.di

import android.app.Application
import androidx.room.Room
import com.example.htnotpadtestapp.data.repo.NoteRepoImpl
import com.example.htnotpadtestapp.data.source.NoteDB
import com.example.htnotpadtestapp.domain.repo.NoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    @Named("test_db")
    fun provideNoteDatabase(app: Application): NoteDB {
        return Room.inMemoryDatabaseBuilder(
            app,
            NoteDB::class.java,
        ).build()
    }

    @Provides
    @Singleton
    @Named("test_repo")
    fun provideNoteRepository(db: NoteDB): NoteRepo {
        return NoteRepoImpl(db.noteDao)
    }

}