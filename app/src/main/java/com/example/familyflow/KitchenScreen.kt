package com.example.familyflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.familyflow.ui.theme.FamilyFlowTheme

class KitchenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyFlowTheme {
                val navController = rememberNavController()
                val tasks = remember { mutableStateListOf<Task>() }

                NavHost(navController = navController, startDestination = "kitchen") {
                    composable("kitchen") {
                        KitchenScreen(
                            navController = navController,
                            tasks = tasks,
                            onTaskUpdated = { updatedTask ->
                                tasks.replaceAll {
                                    if (it.name == updatedTask.name) updatedTask else it
                                }
                            },
                            onTaskDeleted = { taskToDelete ->
                                tasks.remove(taskToDelete)
                            }
                        )
                    }
                    composable("assignTask") {
                        AssignTaskScreen(navController) { newTask ->
                            tasks.add(newTask)
                            navController.navigateUp() // Navigate back to KitchenScreen after task creation
                        }
                    }
                }
            }
        }
    }
}

data class Task(
    val name: String,
    val days: Set<String>,
    val assignedTo: String?,
    var isDone: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitchenScreen(
    navController: NavHostController,
    tasks: List<Task>,
    onTaskUpdated: (Task) -> Unit,
    onTaskDeleted: (Task) -> Unit
) {
    var completedTasks by remember { mutableStateOf(tasks.count { it.isDone }) }
    var totalTasks by remember { mutableStateOf(tasks.size) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kitchen", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation back if needed */ }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF81C784))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF81C784))
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Ensure bottom elements stay at the bottom
        ) {
            Column(modifier = Modifier.weight(1f)) {
                if (tasks.isEmpty()) {
                    Text("No tasks available", fontSize = 20.sp, color = Color.White)
                } else {
                    tasks.forEach { task ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .background(Color(0xFF66BB6A), RoundedCornerShape(8.dp))
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(task.name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                                Text("Assigned to: ${task.assignedTo ?: "Unassigned"}", fontSize = 16.sp, color = Color.White)
                                Text("Days: ${task.days.joinToString(", ")}", fontSize = 16.sp, color = Color.White)
                            }
                            Checkbox(
                                checked = task.isDone,
                                onCheckedChange = {
                                    task.isDone = it
                                    onTaskUpdated(task)
                                    completedTasks = tasks.count { it.isDone }
                                    totalTasks = tasks.size
                                }
                            )
                            IconButton(
                                onClick = {
                                    onTaskDeleted(task)
                                    completedTasks = tasks.count { it.isDone }
                                    totalTasks = tasks.size
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Task",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("assignTask") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text("ADD TASK", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }

            // Task completion counters and image at the bottom
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("$completedTasks ✔", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("${totalTasks - completedTasks} ❌", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.kitchen), // Ensure this image is in res/drawable
                    contentDescription = "Kitchen Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignTaskScreen(navController: NavHostController, onTaskCreated: (Task) -> Unit) {
    var taskName by remember { mutableStateOf("") }
    var selectedDays by remember { mutableStateOf(mutableSetOf<String>()) }
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val familyMembers = listOf("Father", "Mother", "Child 1", "Child 2")
    var selectedMember by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Name", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF81C784))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text("Enter Task Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Do Every", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                daysOfWeek.chunked(4).forEach { rowDays ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        rowDays.forEach { day ->
                            Button(
                                onClick = {
                                    if (selectedDays.contains(day)) {
                                        selectedDays.remove(day)
                                    } else {
                                        selectedDays.add(day)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedDays.contains(day)) Color(0xFF4CAF50) else Color.Gray
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(day, color = Color.White)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Assign To", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                familyMembers.forEach { member ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedMember = member }
                            .padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = selectedMember == member,
                            onCheckedChange = { isChecked ->
                                selectedMember = if (isChecked) member else null
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(member, fontSize = 18.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (taskName.isNotBlank()) {
                        val newTask = Task(
                            name = taskName,
                            days = selectedDays,
                            assignedTo = selectedMember
                        )
                        onTaskCreated(newTask)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("CREATE", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KitchenScreenPreview() {
    FamilyFlowTheme {
        KitchenScreen(
            navController = rememberNavController(),
            tasks = mutableListOf(Task("Example Task", setOf("Mon", "Wed"), "Father")),
            onTaskUpdated = {},
            onTaskDeleted = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AssignTaskScreenPreview() {
    FamilyFlowTheme {
        AssignTaskScreen(navController = rememberNavController()) { }
    }
}