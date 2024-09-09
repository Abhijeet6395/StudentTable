package com.example.daggerhiltapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhiltapp.Repositery.StudentRepository
import com.example.daggerhiltapp.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
//View Model to handle the logic
@HiltViewModel
class StudentViewModel @Inject constructor(private val repository: StudentRepository) : ViewModel() {

    private val _allStudents = MutableStateFlow<List<Student>>(emptyList())
    val allStudents: StateFlow<List<Student>> = _allStudents.asStateFlow()

    // State to hold the retrieved student
    private val _retrievedStudent = MutableStateFlow<Student?>(null)
    val retrievedStudent: StateFlow<Student?> = _retrievedStudent.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allStudents.collect { students ->
                _allStudents.value = students
            }
        }
    }

    fun insertStudent(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    // New method to get a student
    fun getStudent(name: String, studentClass: String, rollNo: Int) = viewModelScope.launch {
        _retrievedStudent.value = repository.getStudent(name, studentClass, rollNo)
    }
}

