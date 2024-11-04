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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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

class BathroomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyFlowTheme {
                val navController = rememberNavController()
                var bathroomTasks by remember { mutableStateOf(mutableListOf<BathroomTask>()) }

                NavHost(navController = navController, startDestination = "bathroom") {
                    composable("bathroom") {
                        BathroomScreen(navController, bathroomTasks) { updatedTask ->
                            bathroomTasks = bathroomTasks.map {
                                if (it.name == updatedTask.name) updatedTask else it
                            }.toMutableList()
                        }
                    }
                    composable("assignTask") {
                        AssignBathroomTaskScreen(navController) { newTask ->
                            bathroomTasks.add(newTask)
                            navController.navigateUp()
                        }
                    }
                }
            }
        }
    }
}

// Unique Task class for BathroomScreen
data class BathroomTask(
    val name: String,
    val days: Set<String>,
    val assignedTo: String?,
    var isDone: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BathroomScreen(
    navController: NavHostController,
    tasks: List<BathroomTask>,
    onTaskUpdated: (BathroomTask) -> Unit
) {
    var completedTasks by remember { mutableStateOf(tasks.count { it.isDone }) }
    var totalTasks by remember { mutableStateOf(tasks.size) }

    // Define a red gradient for the background
    val redGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFCDD2), // Light red
            Color(0xFFE57373), // Medium red
            Color(0xFFD32F2F)  // Dark red
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bathroom", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD32F2F))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = redGradient) // Apply gradient here
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
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
                                .background(brush = redGradient, shape = RoundedCornerShape(8.dp)) // Apply gradient to each task
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
                            IconButton(onClick = {
                                // Remove the task from the list
                                tasks.toMutableList().remove(task)
                                completedTasks = tasks.count { it.isDone }
                                totalTasks = tasks.size
                            }) {
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCDD2)),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text("ADD TASK", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }

            // Task completion counters and image at the bottom
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("$completedTasks ✔", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("${totalTasks - completedTasks} ❌", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.bathroom), // Ensure this image is in res/drawable
                    contentDescription = "Bathroom Image",
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
fun AssignBathroomTaskScreen(
    navController: NavHostController,
    onTaskCreated: (BathroomTask) -> Unit
) {
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
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD32F2F))
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
                                    containerColor = if (selectedDays.contains(day)) Color(0xFFD32F2F) else Color.Gray
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
                        Text(member, fontSize = 18.sp, color = Color.Black)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (taskName.isNotBlank()) {
                        val newTask = BathroomTask(
                            name = taskName,
                            days = selectedDays,
                            assignedTo = selectedMember
                        )
                        onTaskCreated(newTask)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("CREATE", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BathroomScreenPreview() {
    FamilyFlowTheme {
        BathroomScreen(navController = rememberNavController(), tasks = listOf(BathroomTask("Example Task", setOf("Mon", "Wed"), "Mother"))) {}
    }
}

@Preview(showBackground = true)
@Composable
fun AssignBathroomTaskScreenPreview() {
    FamilyFlowTheme {
        AssignBathroomTaskScreen(navController = rememberNavController()) { }
    }
}
