package com.example.myunievents.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.ui.theme.HeaderGreen
import com.example.myunievents.ui.theme.TextWhite
import com.example.myunievents.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyUniTopBar(
    navController: NavController,
    currentScreen: Screen
) {
    TopAppBar(
        title = {
            Text(
                text = "TUS MyUniEvents App",
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = HeaderGreen
        ),
        actions = {
            TextButton(onClick = { navController.navigate(Screen.Home.route) }) {
                Text("HOME", color = TextWhite)
            }
            TextButton(onClick = { navController.navigate(Screen.TrackEvent.route) }) {
                Text("EVENTS", color = TextWhite)
            }
            TextButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                Text("PROFILE", color = TextWhite)
            }
        }
    )
}
