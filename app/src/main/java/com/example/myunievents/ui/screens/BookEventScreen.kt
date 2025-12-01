package com.example.myunievents.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.R
import com.example.myunievents.data.Event
import com.example.myunievents.data.EventRepository
import com.example.myunievents.ui.navigation.Screen
import com.example.myunievents.ui.theme.ButtonRed
import com.example.myunievents.ui.theme.HeaderGreen
import com.example.myunievents.ui.theme.MainGreen
import com.example.myunievents.ui.theme.TextBlack
import kotlinx.coroutines.launch


@Composable
fun BookEventScreen(navController: NavController) {

    var eventName by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var eventTime by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(

        bottomBar = { BottomNavBar(navController) },
        containerColor = MainGreen,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()        // <-- KEY FIX: moves UI up when keyboard opens
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            // BACK BUTTON
            item {
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
            }

            // TITLE
            item {
                Text(
                    text = "BOOK EVENT",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextBlack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    textAlign = TextAlign.Center
                )
            }

            // FORM FIELDS
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    LabeledTextField("Select Event:", eventName) { eventName = it }
                    LabeledTextField("Select Date:", eventDate) { eventDate = it }
                    LabeledTextField("Select Time:", eventTime) { eventTime = it }
                    LabeledTextField("Location:", eventLocation) { eventLocation = it }
                }
            }

            // BUTTONS
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Button(
                        onClick = {
                            eventName = ""
                            eventDate = ""
                            eventTime = ""
                            eventLocation = ""
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                        shape = RoundedCornerShape(30.dp),
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
                                    snackbarHostState.showSnackbar("Event booked!")
                                    navController.popBackStack()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text("Book Event", color = TextBlack)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
            }

            // FOOTER
            item {
                FooterSection()
            }
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
            .padding(22.dp)
    ) {
        Column {
            Text("© 2025 TUS MyUniEvents App", color = TextBlack, fontWeight = FontWeight.Medium)
            Text("Discover upcoming events and stay connected.", color = TextBlack)
        }
    }
}
