package com.example.myunievents.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
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
                // Top bar layout
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "HOME",
                        color = TextBlack,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

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
            }
        }
    }
}
