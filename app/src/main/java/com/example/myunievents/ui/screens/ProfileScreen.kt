package com.example.myunievents.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.R
import com.example.myunievents.ui.theme.*

@Composable
fun ProfileScreen(navController: NavController) {

    // Static sample data for now — you can replace with Firebase user data later
    val username = "Student User"
    val email = "student@tus.ie"

    Scaffold(
        containerColor = MainGreen
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            // ---------- HEADER ----------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(HeaderGreen)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 40.dp,
                            bottomEnd = 40.dp
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // BACK BUTTON
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { navController.popBackStack() },
                        colorFilter = ColorFilter.tint(ButtonRed)
                    )

                    Text(
                        text = "PROFILE",
                        color = TextWhite,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(40.dp)) // balance layout
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Avatar Circle
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(HeaderGreen)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
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
                onClick = { /* future: navigate to edit screen */ },
                colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Edit Profile", color = TextWhite, fontSize = 16.sp)
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
