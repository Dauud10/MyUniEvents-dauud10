package com.example.myunievents.ui.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
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

    // Observe current route so selected state updates correctly
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = HeaderGreen) {
        items.forEach { screen ->
            val isSelected = currentRoute == screen.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(Screen.Home.route)
                            launchSingleTop = true
                        }
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
                                else -> R.drawable.ic_home
                            }
                        ),
                        contentDescription = screen.route,
                        tint = TextWhite,
                        modifier = Modifier.size(24.dp)   // 🔧 FIX: consistent icon size
                    )
                },
                label = {
                    Text(
                        text = when (screen) {
                            Screen.Home -> "Home"
                            Screen.MyEvents -> "Events"
                            Screen.BookEvent -> "Book"
                            Screen.Profile -> "Profile"
                            else -> ""
                        },
                        color = TextWhite
                    )
                }
            )
        }
    }
}
