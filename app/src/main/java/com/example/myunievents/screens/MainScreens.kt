package com.example.myunievents.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.data.AppDatabase
import com.example.myunievents.data.Event
import com.example.myunievents.ui.theme.ButtonRed
import com.example.myunievents.ui.theme.MainGreen
import com.example.myunievents.ui.theme.TextBlack
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { MyUniTopBar(navController, Screen.Home) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "WELCOME TO MYUNIEVENTS APP",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextBlack,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                Row {
                    Button(
                        onClick = { navController.navigate(Screen.TrackEvent.route) },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                        modifier = Modifier.padding(8.dp)
                    ) { Text("TRACK EVENTS") }

                    Button(
                        onClick = { navController.navigate(Screen.BookEvent.route) },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                        modifier = Modifier.padding(8.dp)
                    ) { Text("BOOK EVENT") }
                }
            }
        }
    }
}

@Composable
fun TrackEventScreen(navController: NavController) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = remember { db.eventDao() }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Scaffold(
        topBar = { MyUniTopBar(navController, Screen.TrackEvent) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "TRACK EVENT",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextBlack,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Event Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Event Date") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Event Time") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            name = ""
                            date = ""
                            time = ""
                            location = ""
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed)
                    ) { Text("Cancel") }

                    Button(
                        onClick = {
                            if (name.isBlank() || date.isBlank() || time.isBlank() || location.isBlank()) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Please fill in all fields")
                                }
                            } else {
                                scope.launch {
                                    dao.insertEvent(
                                        Event(
                                            eventName = name,
                                            eventDate = date,
                                            eventTime = time,
                                            location = location
                                        )
                                    )
                                    snackbarHostState.showSnackbar("Event saved")
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed)
                    ) { Text("Save") }
                }
            }
        }
    }
}

@Composable
fun BookEventScreen(navController: NavController) {
    // Simple UI – you can later connect this to Firestore or Room if needed
    var selectedEvent by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { MyUniTopBar(navController, Screen.BookEvent) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "BOOK EVENT",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextBlack,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = selectedEvent,
                    onValueChange = { selectedEvent = it },
                    label = { Text("Select Event") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Select Date") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Select Time") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            selectedEvent = ""
                            date = ""
                            time = ""
                            location = ""
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed)
                    ) { Text("Cancel") }

                    Button(
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Event booked (mock)")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonRed)
                    ) { Text("Book Event") }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = { MyUniTopBar(navController, Screen.Profile) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "PROFILE SCREEN (coming soon)",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = TextBlack
            )
        }
    }
}
