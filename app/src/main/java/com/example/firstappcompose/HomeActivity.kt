package com.example.firstappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstappcompose.ui.theme.FirstappcomposeTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username")
        setContent {
            FirstappcomposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GreetingScreen(username)
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(username: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Hello, ${username ?: "Guest"}", style = MaterialTheme.typography.headlineMedium)
    }
}