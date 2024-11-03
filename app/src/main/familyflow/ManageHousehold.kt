package com.example.familyflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familyflow.ui.ChatsActivity
import com.example.familyflow.ui.theme.FamilyFlowTheme

class ManageHousehold : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyFlowTheme {
                ManageHouseholdScreen(
                    onChatsClick = {
                        val intent = Intent(this, ChatsActivity::class.java)
                        startActivity(intent)
                    },
                    onEventCalendarClick = {
                        val intent = Intent(this, CalendarEvent::class.java)
                        startActivity(intent)
                    },
                    onBudgetClick = {
                        val intent = Intent(this, BudgetActivity::class.java)
                        startActivity(intent)
                    },
                    onManageHouseholdClick = {
                        val intent = Intent(this, mHousehold::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun ManageHouseholdScreen(
    onChatsClick: () -> Unit,
    onEventCalendarClick: () -> Unit,
    onBudgetClick: () -> Unit,
    onManageHouseholdClick: () -> Unit
) {
    var isNavigationVisible by remember { mutableStateOf(false) }
    var isNotificationVisible by remember { mutableStateOf(false) }
    val navigationHeight by animateDpAsState(
        targetValue = if (isNavigationVisible) 300.dp else 0.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val notificationHeight by animateDpAsState(
        targetValue = if (isNotificationVisible) 300.dp else 0.dp,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable(enabled = isNavigationVisible || isNotificationVisible) {
                isNavigationVisible = false
                isNotificationVisible = false
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ManageHouseholdTopAppBar(onNotificationClick = { isNotificationVisible = true })

            Spacer(modifier = Modifier.height(16.dp))

            // Family Name
            Text(
                text = "GOMEZ FAMILY",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Schedule Chore Box with navigation to mHousehold
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF4FC3F7), Color(0xFF039BE5))
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { onManageHouseholdClick() }
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.house),
                        contentDescription = "House Icon",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Schedule Chore",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "• Wash Dishes",
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Room Boxes Row 1
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomBoxItem(
                    text = "Kitchen",
                    backgroundColorStart = Color(0xFF8BC34A),
                    backgroundColorEnd = Color(0xFF689F38),
                    imageResource = R.drawable.kitchen,
                    iconSize = 60.dp,
                    padding = 12.dp,
                    modifier = Modifier.weight(1f)
                )
                CustomBoxItem(
                    text = "Bathroom",
                    backgroundColorStart = Color(0xFFF48FB1),
                    backgroundColorEnd = Color(0xFFC2185B),
                    imageResource = R.drawable.bathroom,
                    iconSize = 60.dp,
                    padding = 12.dp,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Room Boxes Row 2
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomBoxItem(
                    text = "Living Room",
                    backgroundColorStart = Color(0xFFB0BEC5),
                    backgroundColorEnd = Color(0xFF455A64),
                    imageResource = R.drawable.livingroom,
                    iconSize = 60.dp,
                    padding = 12.dp,
                    modifier = Modifier.weight(1f)
                )
                CustomBoxItem(
                    text = "Office",
                    backgroundColorStart = Color(0xFF9575CD),
                    backgroundColorEnd = Color(0xFF512DA8),
                    imageResource = R.drawable.office,
                    iconSize = 60.dp,
                    padding = 12.dp,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Room Boxes Row 3
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomBoxItem(
                    text = "Garden",
                    backgroundColorStart = Color(0xFFD1C4E9),
                    backgroundColorEnd = Color(0xFF7E57C2),
                    imageResource = R.drawable.garden,
                    iconSize = 60.dp,
                    padding = 12.dp,
                    modifier = Modifier.weight(1f)
                )
                CustomBoxItem(
                    text = "",
                    backgroundColorStart = Color(0xFF90CAF9),
                    backgroundColorEnd = Color(0xFF42A5F5),
                    imageResource = R.drawable.add,
                    iconSize = 80.dp,
                    padding = 8.dp,
                    modifier = Modifier.weight(1f),
                    showText = false
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Navigation Button at the Bottom
            if (!isNavigationVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF42A5F5), Color(0xFF1976D2))
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { isNavigationVisible = !isNavigationVisible },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.navigation),
                        contentDescription = "Navigation Icon",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

        // Navigation Drawer
        if (navigationHeight > 0.dp) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(navigationHeight)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF42A5F5), Color(0xFF90CAF9))
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
                    .clickable { isNavigationVisible = false }
            ) {
                Column {
                    Text(
                        text = "Manage Household",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clickable { onManageHouseholdClick() }
                    )
                    Text(
                        text = "Chats",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clickable { onChatsClick() }
                    )
                    Text(
                        text = "Event Calendar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clickable { onEventCalendarClick() }
                    )
                    Text(
                        text = "Budget Port",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clickable { onBudgetClick() }
                    )
                }
            }
        }

        // Notification Drawer
        if (notificationHeight > 0.dp) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(notificationHeight)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF90CAF9), Color(0xFF42A5F5))
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
                    .clickable { isNotificationVisible = false }
            ) {
                Column {
                    Text(
                        text = "Notifications",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    NotificationItem("Chore reminder: Wash the dishes")
                    NotificationItem("New message from Mom")
                    NotificationItem("Upcoming event: Family gathering")
                }
            }
        }
    }
}

@Composable
fun ManageHouseholdTopAppBar(onNotificationClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle user icon click */ }) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "home",
                modifier = Modifier.size(36.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.f),
            contentDescription = "F Logo",
            modifier = Modifier.size(36.dp)
        )

        IconButton(onClick = { onNotificationClick() }) {
            Image(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "Notification",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
fun NotificationItem(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = Color.Black,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun CustomBoxItem(
    text: String,
    backgroundColorStart: Color,
    backgroundColorEnd: Color,
    imageResource: Int,
    iconSize: Dp,
    padding: Dp,
    modifier: Modifier = Modifier,
    showText: Boolean = true
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(backgroundColorStart, backgroundColorEnd)
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = text,
                modifier = Modifier.size(iconSize)
            )
            if (showText && text.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewManageHouseholdScreen() {
    FamilyFlowTheme {
        ManageHouseholdScreen(
            onChatsClick = {},
            onEventCalendarClick = {},
            onBudgetClick = {},
            onManageHouseholdClick = {}
        )
    }
}