package com.example.myunievents.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.data.Event
import com.example.myunievents.data.EventRepository
import com.example.myunievents.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun EventsListScreen(navController: NavController) {

    val scope = rememberCoroutineScope()

    var events by remember { mutableStateOf<List<Event>>(emptyList()) }
    var selectedEvent by remember { mutableStateOf<Event?>(null) }

    // Load events once
    LaunchedEffect(Unit) {
        scope.launch {
            events = EventRepository.getAllEvents()
        }
    }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
                .padding(padding)
        ) {

            // =====================
            // HEADER
            // =====================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(HeaderGreen)
                    .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "MY EVENTS",
                        fontSize = 20.sp,
                        color = TextBlack
                    )

                    Text(
                        text = "BACK",
                        fontSize = 16.sp,
                        color = TextBlack,
                        modifier = Modifier.clickable { navController.popBackStack() }
                    )
                }
            }

            // =====================
            // LIST OF EVENTS
            // =====================
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                items(events) { event ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .clickable { selectedEvent = event },
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = HeaderGreen)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Text(
                                text = event.name,
                                fontSize = 18.sp,
                                color = TextBlack
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Schedule, contentDescription = null, tint = TextBlack)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(event.time, color = TextBlack)
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Event, contentDescription = null, tint = TextBlack)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(event.date, color = TextBlack)
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, tint = TextBlack)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(event.location, color = TextBlack)
                            }
                        }
                    }
                }
            }
        }
    }

    selectedEvent?.let { event ->
        EventDetailsPopup(
            event = event,
            onClose = { selectedEvent = null }
        )
    }
}
