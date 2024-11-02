package com.example.familyflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
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

class ManageHousehold: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyFlowTheme {
                ManageHouseholdScreen()

            }
        }
    }
}

@Composable
fun ManageHouseholdScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(30.dp)

    ) {
        Spacer(modifier = Modifier.padding(
            horizontal = 60.dp,
            vertical = 5.dp))
        // Add space to push content down a bit

        Image(
            painter = painterResource(id = R.drawable.u),
            contentDescription = "UserIcon",
            modifier = Modifier
                .size(55.dp)
                .align(alignment = AbsoluteAlignment.Left),


        )

        Spacer(modifier = Modifier.height(25.dp))


        Text(
            text = "Gomez Family",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2)
        )

        Spacer(modifier = Modifier.height(24.dp))



        GradientButton1(
            text = "Schedule Chore",
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .offset(x = -3.dp)
                .padding(horizontal = 5.dp)

        ) {
            // Handle sign-up click
        }
        Spacer(modifier = Modifier.height(22.dp))



        KitchenButton(
            text = "Kitchen",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .offset(x = -83.dp)
                .padding(horizontal = 85.dp)

        ) {
            // Handle sign-up click
        }
        Spacer(modifier = Modifier.height(22.dp))

        BathroomButton(
            text = "Bathroom",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .offset(x = 83.dp)
                .offset(y = -142.dp)
                .padding(horizontal = 85.dp)

        ) {
            // Handle sign-up click
        }
        Spacer(modifier = Modifier.height(22.dp))

        LivingRoomButton(
            text = "Living Room",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .offset(x = -83.dp)
                .offset(y = -160.dp)
                .padding(horizontal = 85.dp)

        ) {
            // Handle sign-up click
        }
        Spacer(modifier = Modifier.height(-500.dp))

        OfficeButton (
            text = "Office",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .offset(x = 83.dp)
                .offset(y = -250.dp)
                .padding(horizontal = 85.dp)


        ) {
            // Handle sign-up click
        }
    }
}
@Composable
fun GradientButton1(text: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF42A5F5), Color(0xFF1976D2))
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 30.dp)
        )
    }
}
@Composable
fun KitchenButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFBB86FC), Color(0xFF6200EE))
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}
@Composable
fun BathroomButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF42A5F5), Color(0xFF1976D2))
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}
@Composable
fun LivingRoomButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF42A5F5), Color(0xFF1976D2))
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}
@Composable
fun OfficeButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF42A5F5), Color(0xFF1976D2))
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ManageHouseholdScreenPreview() {
    FamilyFlowTheme {
        ManageHouseholdScreen()
    }
}
