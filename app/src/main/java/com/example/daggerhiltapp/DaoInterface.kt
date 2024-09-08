package com.example.daggerhiltapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
//Dao interface for my Students
@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student)
//Selecting Students
    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Student>>
}
