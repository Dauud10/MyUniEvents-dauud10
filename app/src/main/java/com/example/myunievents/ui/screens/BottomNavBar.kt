package com.example.myunievents.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.myunievents.R
import com.example.myunievents.ui.navigation.Screen
import com.example.myunievents.ui.theme.HeaderGreen
import com.example.myunievents.ui.theme.TextWhite

@Composable
fun BottomNavBar(navController: NavController) {

    val items = listOf(
        Screen.Home,
        Screen.MyEvents,
        Screen.BookEvent,
        Screen.Profile
    )

    NavigationBar(containerColor = HeaderGreen) {

        items.forEach { screen ->
            val isSelected = navController.currentDestination?.route == screen.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = when (screen) {
                                Screen.Home -> R.drawable.ic_home
                                Screen.MyEvents -> R.drawable.ic_events
                                Screen.BookEvent -> R.drawable.ic_add
                                Screen.Profile -> R.drawable.ic_users
                            }
                        ),
                        contentDescription = screen.route,
                        tint = TextWhite
                    )
                },
                label = {
                    Text(
                        text = when (screen) {
                            Screen.Home -> "Home"
                            Screen.MyEvents -> "Events"
                            Screen.BookEvent -> "Book"
                            Screen.Profile -> "Profile"
                        },
                        color = TextWhite
                    )
                }
            )
        }
    }
}
