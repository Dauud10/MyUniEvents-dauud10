package com.example.myunievents.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.firebase.AuthManager
import com.example.myunievents.ui.navigation.Screen
import com.example.myunievents.ui.theme.ButtonRed
import com.example.myunievents.ui.theme.MainGreen
import com.example.myunievents.ui.theme.TextBlack
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {

    val context = LocalContext.current

    var username by remember { mutableStateOf("") } // UI only
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "REGISTER",
                color = TextBlack,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = {
                        username = ""
                        email = ""
                        password = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonRed)
                ) { Text("Cancel") }

                Button(
                    onClick = {

                        if (email.isBlank() || password.isBlank()) {
                            scope.launch { snackbarHostState.showSnackbar("Please fill all fields") }
                        } else {
                            AuthManager.register(email, password) { success, error ->
                                if (success) {
                                    Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(Screen.Register.route) { inclusive = true }
                                    }
                                } else {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(error ?: "Registration failed")
                                    }
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonRed)
                ) { Text("Submit") }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navController.navigate(Screen.Login.route)
            }) {
                Text("Already have an account? Log in")
            }
        }
    }
}
