package com.example.daggerhiltapp.Repositery

import com.example.daggerhiltapp.Student
import com.example.daggerhiltapp.StudentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepository @Inject constructor(private val studentDao: StudentDao) {

    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student) {
        studentDao.insertStudent(student)
    }
    suspend fun getStudent(name: String, studentClass: String, rollNo: Int): Student? {
        return studentDao.getStudent(name, studentClass, rollNo)
    }
}
