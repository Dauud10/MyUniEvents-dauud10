package com.example.myunievents.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun BookEventScreen(navController: NavController) {

    var eventName by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var eventTime by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }

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
                    Text("EVENTS", color = TextBlack, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        Text(
                            text = "PROFILE",
                            color = TextBlack,
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Profile.route)
                            }
                        )
                        Text(
                            text = "LOG OUT",
                            color = TextBlack,
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(Screen.BookEvent.route) { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ---------- BACK BUTTON ----------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    colorFilter = ColorFilter.tint(ButtonRed),
                    modifier = Modifier
                        .size(42.dp)
                        .clickable { navController.popBackStack() }
                )
            }

            // ---------- TITLE ----------
            Text(
                text = "BOOK EVENT",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextBlack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            // ---------- FORM ----------
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                LabeledTextField(label = "Select Event:", value = eventName) { eventName = it }
                LabeledTextField(label = "Select Date:", value = eventDate) { eventDate = it }
                LabeledTextField(label = "Select Time:", value = eventTime) { eventTime = it }
                LabeledTextField(label = "Location:", value = eventLocation) { eventLocation = it }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // ---------- BUTTON ROW ----------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        eventName = ""; eventDate = ""; eventTime = ""; eventLocation = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier.width(140.dp)
                ) {
                    Text("Cancel", color = TextBlack)
                }

                Button(
                    onClick = { /* TODO: saving later */ },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier.width(140.dp)
                ) {
                    Text("Book Event", color = TextBlack)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // ---------- FOOTER ----------
            FooterSection()
        }
    }
}

@Composable
fun FooterSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(HeaderGreen)
            .clip(
                RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 40.dp
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text("© 2025 TUS MyUniEvents App", color = TextBlack)
            Text(
                "Discover upcoming events at TUS and stay connected with campus life.",
                color = TextBlack,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun LabeledTextField(label: String, value: String, onChange: (String) -> Unit) {
    Column {
        Text(label, color = TextBlack, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
