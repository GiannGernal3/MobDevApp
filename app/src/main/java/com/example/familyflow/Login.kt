package com.example.familyflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familyflow.ui.theme.FamilyFlowTheme

@OptIn(ExperimentalMaterial3Api::class)
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyFlowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    LoginSignUpScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginSignUpScreen() {
    var isSignUp by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)), // Light blue background
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.f),
                    contentDescription = "Family Flow Logo",
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .clickable {
                            // Navigate to MainActivity when the logo is clicked
                            val intent = Intent(context, HouseholdActivity::class.java)
                            context.startActivity(intent)
                        },
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Title
                Text(
                    text = "Welcome to FamilyFlow",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2),
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tabs (Login / Sign Up)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "login",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (!isSignUp) Color(0xFF1976D2) else Color.Gray,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { isSignUp = false }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    HorizontalDivider(
                        modifier = Modifier
                            .height(24.dp)
                            .width(1.dp)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "sign up",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSignUp) Color(0xFF1976D2) else Color.Gray,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { isSignUp = true }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (isSignUp) {
                    SignUpContent()
                } else {
                    LoginContent()
                }
            }
        }
    }
}

@Composable
fun LoginContent() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    LoginTextField(
        label = "Username/Email",
        value = username,
        onValueChange = { username = it },
        isPassword = false
    )
    Spacer(modifier = Modifier.height(16.dp))
    LoginTextField(
        label = "Password",
        isPassword = true,
        value = password,
        onValueChange = { password = it }
    )

    Spacer(modifier = Modifier.height(24.dp))

    GradientButton(
        text = "LOGIN",
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 32.dp)
    ) {
        // Navigate to HouseholdActivity when the login button is clicked
        val intent = Intent(context, HouseholdActivity::class.java)
        context.startActivity(intent)
    }
}

@Composable
fun SignUpContent() {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginTextField(
        label = "Email",
        value = email,
        onValueChange = { email = it },
        isPassword = false
    )
    Spacer(modifier = Modifier.height(16.dp))
    LoginTextField(
        label = "Username",
        value = username,
        onValueChange = { username = it },
        isPassword = false
    )
    Spacer(modifier = Modifier.height(16.dp))
    LoginTextField(
        label = "Password",
        isPassword = true,
        value = password,
        onValueChange = { password = it }
    )

    Spacer(modifier = Modifier.height(24.dp))

    GradientButton(
        text = "Create Account",
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 32.dp)
    ) {
        // Handle sign-up click
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "or sign up with",
        fontSize = 14.sp,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 8.dp)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.g),
            contentDescription = "Google Sign Up",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    // Handle Google sign-up click
                }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Facebook Sign Up",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    // Handle Facebook sign-up click
                }
        )
    }
}

@Composable
fun GradientButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF42A5F5), Color(0xFF1976D2))
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(label: String, value: String, onValueChange: (String) -> Unit, isPassword: Boolean) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF1976D2),
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color(0xFF1976D2),
            focusedLabelColor = Color(0xFF1976D2),
            unfocusedLabelColor = Color.Gray
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LoginSignUpScreenPreview() {
    FamilyFlowTheme {
        LoginSignUpScreen()
    }
}
