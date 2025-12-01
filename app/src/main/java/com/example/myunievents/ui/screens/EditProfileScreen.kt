package com.example.myunievents.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.ui.navigation.Screen
import com.example.myunievents.ui.theme.*

@Composable
fun EditProfileScreen(navController: NavController) {

    var name by remember { mutableStateOf("") }

    Scaffold(

        containerColor = MainGreen,
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "EDIT PROFILE",
                fontSize = 22.sp,
                color = TextBlack
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Display Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    // In Week 4 you can save to Firebase here
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Save", color = TextBlack)
            }
        }
    }
}
