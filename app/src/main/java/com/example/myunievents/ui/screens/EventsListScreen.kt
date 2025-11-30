package com.example.myunievents.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.data.AppDatabase
import com.example.myunievents.data.Event
import com.example.myunievents.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun EventsListScreen(navController: NavController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dao = remember { AppDatabase.getDatabase(context).eventDao() }

    var events by remember { mutableStateOf<List<Event>>(emptyList()) }

    // Load data from Room
    LaunchedEffect(Unit) {
        scope.launch {
            events = dao.getAllEvents()
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "My Events",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextBlack,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(events) { event ->
                    EventCard(event)
                }
            }
        }
    }
}

@Composable
fun EventCard(event: Event) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {

        // EVENT TITLE
        Text(
            text = event.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextBlack
        )

        Spacer(modifier = Modifier.height(10.dp))

        // TIME
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.AccessTime, contentDescription = null, tint = TextBlack)
            Spacer(modifier = Modifier.width(8.dp))
            Text(event.time, color = TextBlack)
        }

        Spacer(modifier = Modifier.height(6.dp))

        // DATE
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.CalendarMonth, contentDescription = null, tint = TextBlack)
            Spacer(modifier = Modifier.width(8.dp))
            Text(event.date, color = TextBlack)
        }

        Spacer(modifier = Modifier.height(6.dp))

        // LOCATION
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.LocationOn, contentDescription = null, tint = TextBlack)
            Spacer(modifier = Modifier.width(8.dp))
            Text(event.location, color = TextBlack)
        }
    }
}
