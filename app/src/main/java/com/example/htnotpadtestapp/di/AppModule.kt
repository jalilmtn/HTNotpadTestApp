package com.example.htnotpadtestapp.di

import android.app.Application
import androidx.room.Room
import com.example.htnotpadtestapp.data.repo.AlarmSchedulerImpl
import com.example.htnotpadtestapp.data.repo.NoteRepoImpl
import com.example.htnotpadtestapp.data.repo.MyNotificationManagerImpl
import com.example.htnotpadtestapp.data.source.NoteDB
import com.example.htnotpadtestapp.domain.repo.AlarmScheduler
import com.example.htnotpadtestapp.domain.repo.MyNotificationManager
import com.example.htnotpadtestapp.domain.repo.NoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDB(application: Application) =
        Room.databaseBuilder(
            application,
            NoteDB::class.java,
            NoteDB.DB_NAME
        ).build()

    @Provides
    @Singleton
    fun provideNoteRepo(db: NoteDB): NoteRepo = NoteRepoImpl(db.noteDao)

    @Provides
    @Singleton
    fun provideNotificationManager(application: Application): MyNotificationManager =
        MyNotificationManagerImpl(application)

    @Provides
    @Singleton
    fun provideAlarmScheduler(application: Application): AlarmScheduler =
        AlarmSchedulerImpl(application)


}