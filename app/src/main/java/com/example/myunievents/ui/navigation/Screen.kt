package com.example.myunievents.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object BookEvent : Screen("book_event")
    data object MyEvents : Screen("my_events")
    data object Profile : Screen("profile")
    data object EditProfile : Screen("edit_profile")

}
