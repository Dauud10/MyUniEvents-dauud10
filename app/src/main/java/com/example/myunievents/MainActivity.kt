package com.example.myunievents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myunievents.screens.*
import com.example.myunievents.ui.theme.MyUniEventsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyUniEventsApp()
        }
    }
}

@Composable
fun MyUniEventsApp() {
    MyUniEventsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.Login.route
            ) {
                composable(Screen.Login.route) { LoginScreen(navController) }
                composable(Screen.Register.route) { RegisterScreen(navController) }
                composable(Screen.Home.route) { HomeScreen(navController) }
                composable(Screen.TrackEvent.route) { TrackEventScreen(navController) }
                composable(Screen.BookEvent.route) { BookEventScreen(navController) }
                composable(Screen.Profile.route) { ProfileScreen(navController) }
            }
        }
    }
}
