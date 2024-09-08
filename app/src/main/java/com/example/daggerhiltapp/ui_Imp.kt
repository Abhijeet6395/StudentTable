package com.example.daggerhiltapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.daggerhiltapp.ViewModel.StudentViewModel
//Ui of the app
@Composable
fun StudentAppScreen(studentViewModel: StudentViewModel) {
    val students by studentViewModel.allStudents.collectAsState()

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var studentClass by remember { mutableStateOf(TextFieldValue("")) }
    var rollNo by remember { mutableStateOf(TextFieldValue("")) }

    //  show error messages
    var showNameError by remember { mutableStateOf(false) }
    var showClassError by remember { mutableStateOf(false) }
    var showRollNoError by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(students) { student ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Name: ${student.name}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Class: ${student.studentClass}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Roll No: ${student.rollNo}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = name,
            onValueChange = {
                name = it
                showNameError = it.text.isEmpty()
            },
            label = { Text("Name") },
            isError = showNameError,
            modifier = Modifier.fillMaxWidth()
        )
        if (showNameError) {
            Text("Name is required", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = studentClass,
            onValueChange = {
                studentClass = it
                showClassError = it.text.isEmpty()
            },
            label = { Text("Class") },
            isError = showClassError,
            modifier = Modifier.fillMaxWidth()
        )
        if (showClassError) {
            Text("Class is required", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = rollNo,
            onValueChange = {
                rollNo = it
                showRollNoError = it.text.isEmpty()
            },
            label = { Text("Roll No") },
            isError = showRollNoError,
            modifier = Modifier.fillMaxWidth()
        )
        if (showRollNoError) {
            Text("Roll No is required", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.text.isNotEmpty() && studentClass.text.isNotEmpty() && rollNo.text.isNotEmpty()) {
                    val student = Student(
                        name = name.text,
                        studentClass = studentClass.text,
                        rollNo = rollNo.text.toInt()
                    )
                    studentViewModel.insertStudent(student)
                    name = TextFieldValue("")
                    studentClass = TextFieldValue("")
                    rollNo = TextFieldValue("")
                } else {
                    showNameError = name.text.isEmpty()
                    showClassError = studentClass.text.isEmpty()
                    showRollNoError = rollNo.text.isEmpty()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            enabled = !(name.text.isEmpty() || studentClass.text.isEmpty() || rollNo.text.isEmpty())
        ) {
            Text(text = "INSERT")
        }
    }
}
