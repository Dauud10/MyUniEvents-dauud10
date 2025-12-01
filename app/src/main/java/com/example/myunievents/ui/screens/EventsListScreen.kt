package com.example.myunievents.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.data.Event
import com.example.myunievents.data.EventRepository
import com.example.myunievents.ui.navigation.Screen
import com.example.myunievents.ui.theme.MainGreen
import com.example.myunievents.ui.theme.TextBlack

@Composable
fun EventsListScreen(navController: NavController) {

    var selectedEvent by remember { mutableStateOf<Event?>(null) }

    val events by EventRepository
        .getAllEvents()
        .collectAsState(initial = emptyList())

    Scaffold(

        bottomBar = { BottomNavBar(navController) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
                .padding(padding)
                .padding(16.dp)
        ) {

            // Screen Title Under TopBar
            Text(
                text = "My Events",
                fontSize = 22.sp,
                color = TextBlack,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(events) { event ->
                    EventCard(event) {
                        selectedEvent = event
                    }
                }
            }
        }
    }

    // Popup
    selectedEvent?.let { event ->
        EventDetailsPopup(
            event = event,
            onClose = { selectedEvent = null }
        )
    }
}

@Composable
fun EventCard(event: Event, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .clickable { onClick() }
    ) {

        Text(
            text = event.name,
            fontSize = 20.sp,
            color = TextBlack
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Schedule, contentDescription = null, tint = TextBlack)
            Spacer(modifier = Modifier.width(8.dp))
            Text(event.time, color = TextBlack)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Event, contentDescription = null, tint = TextBlack)
            Spacer(modifier = Modifier.width(8.dp))
            Text(event.date, color = TextBlack)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.LocationOn, contentDescription = null, tint = TextBlack)
            Spacer(modifier = Modifier.width(8.dp))
            Text(event.location, color = TextBlack)
        }
    }
}
