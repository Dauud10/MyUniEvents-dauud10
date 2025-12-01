package com.example.myunievents.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myunievents.data.Event
import com.example.myunievents.ui.theme.ButtonRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsPopup(event: Event, onClose: () -> Unit) {

    ModalBottomSheet(
        onDismissRequest = onClose,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = event.name,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Date: ${event.date}", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
            Text("Time: ${event.time}", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
            Text("Location: ${event.location}", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = onClose,
                colors = ButtonDefaults.buttonColors(containerColor = ButtonRed),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Close", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
