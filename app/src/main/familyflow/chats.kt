package com.example.familyflow.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familyflow.R
import com.example.familyflow.ui.theme.FamilyFlowTheme

class ChatsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyFlowTheme {
                ChatPage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatPage() {
    var selectedChat by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    val chatList = listOf(
        "Dad" to "Fix your Bedroom",
        "Mom" to "Fix your Bedroom"
    )
    val filteredChats = chatList.filter { it.first.contains(searchQuery, ignoreCase = true) }

    if (selectedChat != null) {
        ChatBox(chatWith = selectedChat!!) {
            selectedChat = null // Close the chat when the back button is clicked
        }
    } else {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            modifier = Modifier.size(32.dp).padding(start = 8.dp)
                        )
                    },
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.f),
                            contentDescription = "Logo",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF4FC3F7), // Light blue
                                Color(0xFF039BE5)  // Dark blue
                            )
                        )
                    )
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                CustomSearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Chats",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                filteredChats.forEach { (name, status) ->
                    ChatItem(name = name, status = status, avatarRes = if (name == "Dad") R.drawable.dad else R.drawable.mom) {
                        selectedChat = name
                    }
                }
            }
        }
    }
}

@Composable
fun CustomSearchBar(query: String, onQueryChange: (String) -> Unit) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        textStyle = TextStyle(color = Color.Gray, fontSize = 16.sp),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(Color.White, shape = RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                if (query.isEmpty()) {
                    Text("Search", color = Color.Gray)
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun ChatItem(name: String, status: String, avatarRes: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = "$name's avatar",
            modifier = Modifier
                .size(48.dp)
                .background(Color.White, shape = RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = status, fontSize = 14.sp, color = Color.LightGray)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBox(chatWith: String, onBack: () -> Unit) {
    val avatarRes = if (chatWith == "Dad") R.drawable.dad else R.drawable.mom
    var userMessage by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Chat with $chatWith", fontSize = 20.sp, color = Color.White)
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        modifier = Modifier.size(32.dp).clickable { onBack() }
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF039BE5)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            // Display chat messages
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = "$chatWith's avatar",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Fix your Bedroom",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Push chat bar to the bottom

            // Chat bar for user input
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.3f), shape = RoundedCornerShape(24.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = userMessage,
                    onValueChange = { userMessage = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                    decorationBox = { innerTextField ->
                        if (userMessage.text.isEmpty()) {
                            Text("Type a message...", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )
                Text(
                    text = "Send",
                    color = Color(0xFF039BE5),
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            // Handle sending message logic here
                            userMessage = TextFieldValue("") // Clear input after sending
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatPage() {
    FamilyFlowTheme {
        ChatPage()
    }
}
