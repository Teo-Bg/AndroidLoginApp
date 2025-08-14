package com.example.firstappcompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstappcompose.ui.theme.FirstappcomposeTheme
import kotlin.apply

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val isDarkTheme = sharedPref.getBoolean("darkTheme", false)
        setContent {
            FirstappcomposeTheme(darkTheme = isDarkTheme) {
                SettingsScreen(
                   // isDarkTheme = isDarkTheme,
                   // onThemeChange = { theme ->
                        //isDarkTheme = theme
                        //editor.putBoolean("darkTheme", theme).apply()
                    //},
                    onBackClick = { finish() }
                )
            }
        }
    }
}

@Composable
fun SettingsScreen(
    //isDarkTheme: Boolean,
    //onThemeChange: (Boolean) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Choose Theme", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
/*
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = !isDarkTheme,
                onClick = { onThemeChange(false) }
            )
            Text("Light Theme")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = isDarkTheme,
                onClick = { onThemeChange(true) }
            )
            Text("Dark Theme")
        }

        Spacer(Modifier.height(24.dp))


 */
        Button(onClick = onBackClick, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Login")
        }
    }
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