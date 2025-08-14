package com.example.firstappcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstappcompose.ui.theme.FirstappcomposeTheme

class SettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val isDarkTheme = sharedPref.getBoolean("darkTheme", false)

        setContent {
            var darkThemeEnabled by remember { mutableStateOf(isDarkTheme) }

            FirstappcomposeTheme(darkTheme = darkThemeEnabled) {
                ThemeToggleButton(
                    darkThemeEnabled = darkThemeEnabled,
                    onToggle = { newTheme ->
                        darkThemeEnabled = newTheme
                        sharedPref.edit()
                            .putBoolean("darkTheme", newTheme)
                            .apply()
                    },
                    onBackToMain = {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun ThemeToggleButton(
    darkThemeEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    onBackToMain: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(onClick = { onToggle(!darkThemeEnabled) }) {
                        Text(text = if (darkThemeEnabled) "Light" else "Dark")
                    }
                    Button(onClick = onBackToMain) {
                        Text(text = "Back to Main")
                    }
                }
            }
        }
    )
}
/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstappcomposeTheme {
        Greeting("Android")
    }
}

 */