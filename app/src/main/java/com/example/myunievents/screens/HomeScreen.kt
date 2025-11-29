package com.example.myunievents.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.ui.theme.ButtonRed
import com.example.myunievents.ui.theme.HeaderGreen
import com.example.myunievents.ui.theme.MainGreen
import com.example.myunievents.ui.theme.TextBlack

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
            // ========================
            // HEADER
            // ========================
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
                        Text(
                            text = "HOME",
                            color = TextBlack,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "EVENTS",
                            color = TextBlack,
                            fontSize = 18.sp
                        )
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

            // ========================
            // MAIN CONTENT
            // ========================
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
                        onClick = { navController.navigate(Screen.TrackEvent.route) },
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

                Spacer(modifier = Modifier.height(60.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ========================
            // FOOTER
            // ========================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HeaderGreen)
                    .clip(
                        RoundedCornerShape(
                            topStart = 40.dp,
                            topEnd = 40.dp
                        )
                    )
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                    // LEFT COLUMN
                    Column {
                        Text("© 2025 TUS MyUniEvents App", color = TextBlack, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Contact US", color = TextBlack, fontSize = 14.sp)
                        Row {
                            Text("Email | ", color = TextBlack)
                            Text("PhoneNo", color = TextBlack)
                        }
                    }

                    // CENTER COLUMN
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("About", color = TextBlack, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Discover upcoming events at TUS\nand stay connected with campus life.",
                            color = TextBlack,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    // RIGHT COLUMN
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Social Media", color = TextBlack, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Text("📘", fontSize = 20.sp)
                            Text("📸", fontSize = 20.sp)
                            Text("🐦", fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}
