package com.example.myunievents.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object TrackEvent : Screen("track_event")
    data object BookEvent : Screen("book_event")
    data object Profile : Screen("profile")
}
