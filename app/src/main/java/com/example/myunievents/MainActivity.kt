package com.example.myunievents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberSaveable

import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myunievents.ui.navigation.Screen
import com.example.myunievents.ui.screens.BookEventScreen
import com.example.myunievents.ui.screens.EditProfileScreen
import com.example.myunievents.ui.screens.EventsListScreen
import com.example.myunievents.ui.screens.HomeScreen
import com.example.myunievents.ui.screens.LoginScreen
import com.example.myunievents.ui.screens.ProfileScreen
import com.example.myunievents.ui.screens.RegisterScreen
import com.example.myunievents.ui.theme.MyUniEventsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {


            var darkMode by rememberSaveable { mutableStateOf(false) }


            MyUniEventsTheme(darkTheme = darkMode) {

                // Navigation controller
                val navController = rememberNavController()

                // Navigation graph
                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {
                    composable(Screen.Login.route) {
                        LoginScreen(navController)
                    }

                    composable(Screen.Register.route) {
                        RegisterScreen(navController)
                    }

                    composable(Screen.Home.route) {
                        HomeScreen(navController)
                    }

                    composable(Screen.MyEvents.route) {
                        EventsListScreen(navController)
                    }

                    composable(Screen.BookEvent.route) {
                        BookEventScreen(navController)
                    }

                    // DARK MODE STATE TO PROFILE SCREEN
                    composable(Screen.Profile.route) {
                        ProfileScreen(
                            navController = navController,
                            darkMode = darkMode,
                            onDarkModeChange = { darkMode = it }
                        )
                    }

                    composable(Screen.EditProfile.route) {
                        EditProfileScreen(navController)
                    }
                }
            }
        }
    }
}
