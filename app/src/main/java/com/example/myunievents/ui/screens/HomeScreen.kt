package com.example.myunievents.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.ui.theme.*
import com.example.myunievents.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MainGreen)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // HEADER
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
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
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                        Text("HOME", color = TextBlack, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("EVENTS", color = TextBlack, fontSize = 18.sp)
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        Text(
                            text = "PROFILE",
                            color = TextBlack,
                            fontSize = 16.sp,
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Profile.route)
                            }
                        )
                        Text(
                            text = "LOG OUT",
                            color = TextBlack,
                            fontSize = 16.sp,
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(Screen.Home.route) { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }

            // MAIN CONTENT
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "WELCOME TO MYUNIEVENTS APP",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextBlack
                )

                Spacer(modifier = Modifier.height(80.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Button(
                        onClick = { navController.navigate(Screen.MyEvents.route) }, // FIXED
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                        modifier = Modifier
                            .width(180.dp)
                            .height(60.dp)
                    ) {
                        Text("TRACK EVENTS", color = TextBlack)
                    }

                    Button(
                        onClick = { navController.navigate(Screen.BookEvent.route) },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                        modifier = Modifier
                            .width(180.dp)
                            .height(60.dp)
                    ) {
                        Text("BOOK EVENT", color = TextBlack)
                    }
                }
            }
        }
    }
}
