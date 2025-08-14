package com.example.firstappcompose

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.firstappcompose.ui.theme.FirstappcomposeTheme


class MainActivity : ComponentActivity() {

    private var registeredUsername: String? = null
    private var registeredEmail: String? = null
    private var registeredPassword: String? = null

    private val registerActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            registeredUsername = data?.getStringExtra("username")
            registeredEmail = data?.getStringExtra("email")
            registeredPassword = data?.getStringExtra("password")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val sharedPrefEmail = sharedPref.getString("email", "") ?: ""
        val isDarkTheme = sharedPref.getBoolean("darkTheme", false)

        setContent {
            FirstappcomposeTheme(darkTheme = isDarkTheme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LoginScreen(
                        initialEmail = sharedPrefEmail,
                        onLoginSuccess = { username ->
                            val intent = Intent(this, HomeActivity::class.java)
                            intent.putExtra("username", username)
                            startActivity(intent)
                        },
                        onRegisterClick = {
                            val intent = Intent(this, RegisterActivity::class.java)
                            registerActivityLauncher.launch(intent)
                        },
                        onSettingsClick = {
                            val intent = Intent(this, SettingActivity::class.java)
                            startActivity(intent)
                        },
                        getRegisteredCredentials = {
                            Triple(registeredEmail, registeredPassword, registeredUsername)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLoginSuccess: (String?) -> Unit,
    onRegisterClick: () -> Unit,
    onSettingsClick: () -> Unit, // NEW callback
    getRegisteredCredentials: () -> Triple<String?, String?, String?>,
    initialEmail: String = ""
) {
    var email by rememberSaveable { mutableStateOf(initialEmail) }
    var password by rememberSaveable { mutableStateOf("") }
    var errorVisible by remember { mutableStateOf(false) }

    val (registeredEmail, registeredPassword, registeredUsername) = getRegisteredCredentials()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        IconButton(
            onClick = { onSettingsClick() },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(Icons.Default.Settings, contentDescription = "Settings")
        }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.height(90.dp))

            Text("Welcome!", style = MaterialTheme.typography.headlineMedium)

            Spacer(Modifier.height(34.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(28.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(28.dp))

            Button(
                onClick = {
                    if (email == registeredEmail && password == registeredPassword) {
                        onLoginSuccess(registeredUsername)
                    } else {
                        errorVisible = true
                    }
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            if (errorVisible) {
                Spacer(Modifier.height(16.dp))
                Text("Invalid email or password", color = Color.Red)
            }

            Spacer(Modifier.height(140.dp))

            TextButton(onClick = onRegisterClick) {
                Text("Don't have an account? Sign up", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}