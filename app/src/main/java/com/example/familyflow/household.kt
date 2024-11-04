package com.example.familyflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familyflow.ui.theme.FamilyFlowTheme

class HouseholdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyFlowTheme {
                HouseholdScreen(
                    onAddHouseholdClick = { navigateToCHousehold() },
                    onEnterExistingHouseholdClick = { navigateToEHousehold() }
                )
            }
        }
    }

    private fun navigateToCHousehold() {
        // Create an Intent to start the CHouseholdActivity
        val intent = Intent(this, CHouseholdActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToEHousehold() {
        // Create an Intent to start the EHouseholdActivity
        val intent = Intent(this, EHouseholdActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun HouseholdScreen(
    onAddHouseholdClick: () -> Unit,
    onEnterExistingHouseholdClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp)) // Add space to push content down a bit

        Image(
            painter = painterResource(id = R.drawable.f),
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Household",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2) // Blue color matching the login screen
        )

        Spacer(modifier = Modifier.height(32.dp)) // Space between title and first button

        GradientButton(
            text = "+ Add Household",
            onClick = onAddHouseholdClick
        )

        Spacer(modifier = Modifier.height(8.dp)) // Reduced space between the buttons

        GradientButton(
            text = "Enter Existing Household",
            onClick = onEnterExistingHouseholdClick
        )
    }
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp) // Keep the larger height for the button
            .padding(horizontal = 24.dp), // Adjust padding to match the style
        shape = RoundedCornerShape(16.dp), // Larger corner radius for a rounded look
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
                            Color(0xFF4FC3F7), // Light blue start
                            Color(0xFF039BE5)  // Dark blue end
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text, color = Color.White, fontSize = 16.sp) // Adjust text size to fit larger button
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HouseholdScreenPreview() {
    FamilyFlowTheme {
        HouseholdScreen(
            onAddHouseholdClick = { /* Do nothing in preview */ },
            onEnterExistingHouseholdClick = { /* Do nothing in preview */ }
        )
    }
}
