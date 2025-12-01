package com.example.myunievents.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.R
import com.example.myunievents.ui.navigation.Screen
import com.example.myunievents.ui.theme.ButtonRed
import com.example.myunievents.ui.theme.HeaderGreen
import com.example.myunievents.ui.theme.MainGreen
import com.example.myunievents.ui.theme.TextBlack
import com.example.myunievents.ui.theme.TextWhite

@Composable
fun ProfileScreen(navController: NavController) {

    // TEMP USER DATA
    val username = "Student User"
    val email = "student@tus.ie"

    // Dark mode temp toggle
    var darkMode by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                currentScreen = Screen.Profile
            )
        },
        containerColor = MainGreen,
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // Avatar
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(HeaderGreen)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_users),
                    contentDescription = "User Icon",
                    tint = TextWhite,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // USER INFO FIELDS
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                ProfileField(label = "Username", value = username)
                ProfileField(label = "Email", value = email)
            }

            Spacer(modifier = Modifier.height(40.dp))

            // EDIT PROFILE BUTTON
            Button(
                onClick = {
                    navController.navigate("edit_profile")
                },
                colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Edit Profile", color = TextWhite, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(30.dp))

            // DARK MODE TOGGLE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Dark Mode",
                    fontSize = 16.sp,
                    color = TextBlack,
                    fontWeight = FontWeight.Medium
                )
                Switch(
                    checked = darkMode,
                    onCheckedChange = { darkMode = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = ButtonRed,
                        checkedTrackColor = HeaderGreen
                    )
                )
            }
        }
    }
}

@Composable
fun ProfileField(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = TextBlack
        )
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(10.dp)
        )
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(containerColor = HeaderGreen) {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Home.route) },
            icon = {
                Icon(painterResource(R.drawable.ic_home), null, tint = TextWhite)
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.MyEvents.route) },
            icon = {
                Icon(painterResource(R.drawable.ic_events), null, tint = TextWhite)
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.BookEvent.route) },
            icon = {
                Icon(painterResource(R.drawable.ic_add), null, tint = TextWhite)
            }
        )

        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(painterResource(R.drawable.ic_users), null, tint = TextWhite)
            }
        )
    }
}
