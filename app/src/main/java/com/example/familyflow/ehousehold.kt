package com.example.familyflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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

class EHouseholdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyFlowTheme {
                EnterHouseholdScreen()
            }
        }
    }
}

@Composable
fun EnterHouseholdScreen() {
    var code by remember { mutableStateOf("") }

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
            text = "Enter Household",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Code Input Box with Gradient Background
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(60.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF4FC3F7), // Light blue
                            Color(0xFF039BE5)  // Dark blue
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = code.padEnd(4, 'X').take(4), // Displaying "XXXX" or the code
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Keypad Layout
        Keypad(onNumberClick = { number ->
            if (code.length < 4) {
                code += number
            }
        }, onDeleteClick = {
            if (code.isNotEmpty()) {
                code = code.dropLast(1)
            }
        }, onEnterClick = {
            // Handle enter action
        })
    }
}

@Composable
fun Keypad(onNumberClick: (String) -> Unit, onDeleteClick: () -> Unit, onEnterClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val rows = listOf(
            listOf("1", "2 ABC", "3 DEF"),
            listOf("4 GHI", "5 JKL", "6 MNO"),
            listOf("7 PQRS", "8 TUV", "9 WXYZ"),
            listOf("ENTER", "0", "DELETE")
        )

        for (row in rows) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (item in row) {
                    KeypadButton(
                        text = item,
                        isSpecialButton = item == "ENTER" || item == "DELETE",
                        onClick = {
                            when (item) {
                                "DELETE" -> onDeleteClick()
                                "ENTER" -> onEnterClick()
                                else -> onNumberClick(item.first().toString())
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun KeypadButton(text: String, isSpecialButton: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(if (isSpecialButton) 70.dp else 80.dp)
            .background(
                color = Color(0xFF1976D2).copy(alpha = 0.1f), // Light background for button
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val parts = text.split(" ")
            Text(
                text = parts[0], // Main number
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                textAlign = TextAlign.Center
            )
            if (parts.size > 1) {
                Text(
                    text = parts[1], // Sub-text like "ABC"
                    fontSize = 12.sp,
                    color = Color(0xFF1976D2),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnterHouseholdScreenPreview() {
    FamilyFlowTheme {
        EnterHouseholdScreen()
    }
}
