package com.example.familyflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familyflow.ui.theme.FamilyFlowTheme

class CHouseholdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyFlowTheme {
                CreateHouseholdScreen()
            }
        }
    }
}

@Composable
fun CreateHouseholdScreen() {
    val householdName = remember { mutableStateOf("") }
    val adminRole = remember { mutableStateOf("") }
    val members = remember { mutableStateOf("") }
    val householdNumber = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.f), // Assuming the logo is named "f"
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Create Household",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // OutlinedTextField for Household Name
        OutlinedTextField(
            value = householdName.value,
            onValueChange = { householdName.value = it },
            label = { Text("Household Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for Admin Role
        OutlinedTextField(
            value = adminRole.value,
            onValueChange = { adminRole.value = it },
            label = { Text("Admin Role") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for Adding Members
        OutlinedTextField(
            value = members.value,
            onValueChange = { members.value = it },
            label = { Text("Add Members") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for Household Number
        OutlinedTextField(
            value = householdNumber.value,
            onValueChange = { householdNumber.value = it },
            label = { Text("Household Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Create Household Button
        CHouseholdGradientButton(
            text = "Create Household",
            onClick = { /* Handle Create Household action */ }
        )
    }
}

@Composable
fun CHouseholdGradientButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF4FC3F7), // Light blue
                            Color(0xFF039BE5)  // Dark blue
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text, color = Color.White, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateHouseholdScreenPreview() {
    FamilyFlowTheme {
        CreateHouseholdScreen()
    }
}
