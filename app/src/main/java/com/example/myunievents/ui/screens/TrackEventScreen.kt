package com.example.myunievents.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.R
import com.example.myunievents.data.Event
import com.example.myunievents.data.EventRepository
import com.example.myunievents.ui.theme.ButtonRed
import com.example.myunievents.ui.theme.HeaderGreen
import com.example.myunievents.ui.theme.MainGreen
import com.example.myunievents.ui.theme.TextBlack
import kotlinx.coroutines.launch

@Composable
fun TrackEventScreen(navController: NavController) {

    var eventName by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var eventTime by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
                .padding(padding)
        ) {

            // ---------------- HEADER --------------------
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
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // Back button
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = ButtonRed,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { navController.popBackStack() }
                    )

                    Text(
                        text = "TRACK EVENT",
                        color = TextBlack,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.width(32.dp))
                }
            }

            // ---------------- FORM --------------------
            Spacer(modifier = Modifier.height(40.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {

                LabeledInput("Event Name:", eventName) { eventName = it }
                Spacer(modifier = Modifier.height(20.dp))

                LabeledInput("Event Date:", eventDate) { eventDate = it }
                Spacer(modifier = Modifier.height(20.dp))

                LabeledInput("Event Time:", eventTime) { eventTime = it }
                Spacer(modifier = Modifier.height(20.dp))

                LabeledInput("Location:", eventLocation) { eventLocation = it }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // ---------------- BUTTONS --------------------
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Button(
                    onClick = {
                        eventName = ""
                        eventDate = ""
                        eventTime = ""
                        eventLocation = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                    modifier = Modifier.width(140.dp)
                ) {
                    Text("Cancel", color = TextBlack)
                }

                Button(
                    onClick = {
                        if (eventName.isBlank() || eventDate.isBlank() ||
                            eventTime.isBlank() || eventLocation.isBlank()
                        ) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Please fill all fields")
                            }
                        } else {

                            val newEvent = Event(
                                name = eventName,
                                date = eventDate,
                                time = eventTime,
                                location = eventLocation
                            )

                            scope.launch {
                                EventRepository.insertEvent(newEvent)
                                snackbarHostState.showSnackbar("Event saved!")
                                navController.popBackStack()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                    modifier = Modifier.width(140.dp)
                ) {
                    Text("Save", color = TextBlack)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ---------------- FOOTER --------------------
            FooterSection()
        }
    }
}

@Composable
fun LabeledInput(label: String, value: String, onChange: (String) -> Unit) {
    Column {
        Text(text = label, color = TextBlack, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun FooterSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(HeaderGreen)
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .padding(22.dp)
    ) {
        Column {
            Text("© 2025 TUS MyUniEvents App", color = TextBlack, fontWeight = FontWeight.Medium)
            Text("Discover upcoming events and stay connected.", color = TextBlack)
        }
    }
}
