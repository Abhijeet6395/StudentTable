package com.example.daggerhiltapp.DaggerHilt

import android.content.Context
import androidx.room.Room
import com.example.daggerhiltapp.Repositery.StudentRepository
import com.example.daggerhiltapp.StudentDao
import com.example.daggerhiltapp.StudentDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    // Provide the database instance
    //my module For Injection
    fun provideDatabase(@ApplicationContext context: Context): StudentDatabase {
        return Room.databaseBuilder(
            context,
            StudentDatabase::class.java,
            "student_db"
        ).build()
    }

//StudentDatabase and StudentDao instances using Dagger Hilt.
    @Provides
    @Singleton
    fun provideStudentDao(database: StudentDatabase): StudentDao {
        return database.studentDao()
    }

    // StudentRepository instance
    @Provides
    @Singleton
    fun provideStudentRepository(studentDao: StudentDao): StudentRepository {
        return StudentRepository(studentDao)
    }
}
